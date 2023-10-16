module sui_blog_example::blog_update_logic {
    use std::string::String;
    use sui::object::ID;
    use sui::tx_context::TxContext;
    use sui_blog_example::blog;
    use sui_blog_example::blog_updated;

    friend sui_blog_example::blog_aggregate;

    public(friend) fun verify(
        name: String,
        articles: vector<ID>,
        blog: &blog::Blog,
        ctx: &TxContext,
    ): blog::BlogUpdated {
        let _ = ctx;
        blog::new_blog_updated(
            blog,
            name,
            articles,
        )
    }

    public(friend) fun mutate(
        blog_updated: &blog::BlogUpdated,
        blog: &mut blog::Blog,
        ctx: &TxContext, // modify the reference to mutable if needed
    ) {
        let name = blog_updated::name(blog_updated);
        let articles = blog_updated::articles(blog_updated);
        let _ = ctx;
        blog::set_name(blog, name);
        blog::set_articles(blog, articles);
    }

}
