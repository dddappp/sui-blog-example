module sui_blog_example::blog_create_logic {
    use std::string::String;
    use sui::object::ID;
    use sui::tx_context::TxContext;
    use sui_blog_example::blog;
    use sui_blog_example::blog_created;

    friend sui_blog_example::blog_aggregate;

    public(friend) fun verify(
        name: String,
        articles: vector<ID>,
        ctx: &mut TxContext,
    ): blog::BlogCreated {
        let _ = ctx;
        blog::new_blog_created(
            name,
            articles,
        )
    }

    public(friend) fun mutate(
        blog_created: &blog::BlogCreated,
        ctx: &mut TxContext,
    ): blog::Blog {
        let name = blog_created::name(blog_created);
        let articles = blog_created::articles(blog_created);
        blog::new_blog(
            name,
            articles,
            ctx,
        )
    }

}
