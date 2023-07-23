module sui_blog_example::blog_add_article_logic {
    use std::vector;

    use sui::object::ID;
    use sui::tx_context::TxContext;

    use sui_blog_example::article_added_to_blog;
    use sui_blog_example::blog;

    friend sui_blog_example::blog_aggregate;

    public(friend) fun verify(
        article_id: ID,
        blog: &blog::Blog,
        ctx: &TxContext,
    ): blog::ArticleAddedToBlog {
        let _ = ctx;
        blog::new_article_added_to_blog(
            blog,
            article_id,
        )
    }

    public(friend) fun mutate(
        article_added_to_blog: &blog::ArticleAddedToBlog,
        blog: blog::Blog,
        ctx: &TxContext, // modify the reference to mutable if needed
    ): blog::Blog {
        let article_id = article_added_to_blog::article_id(article_added_to_blog);
        let _ = ctx;
        let articles = blog::articles(&blog);
        if (!vector::contains(&articles, &article_id)) {
            vector::push_back(&mut articles, article_id);
            blog::set_articles(&mut blog, articles);
        };
        blog
    }
}
