module sui_blog_example::blog_remove_article_logic {
    use std::vector;

    use sui::object::ID;
    use sui::tx_context::TxContext;

    use sui_blog_example::article_removed_from_blog;
    use sui_blog_example::blog;

    friend sui_blog_example::blog_aggregate;

    public(friend) fun verify(
        article_id: ID,
        blog: &blog::Blog,
        ctx: &TxContext,
    ): blog::ArticleRemovedFromBlog {
        let _ = ctx;
        blog::new_article_removed_from_blog(
            blog,
            article_id,
        )
    }

    public(friend) fun mutate(
        article_removed_from_blog: &blog::ArticleRemovedFromBlog,
        blog: blog::Blog,
        ctx: &TxContext, // modify the reference to mutable if needed
    ): blog::Blog {
        let article_id = article_removed_from_blog::article_id(article_removed_from_blog);
        let _ = ctx;
        let articles = blog::articles(&blog);
        let (found, idx) = vector::index_of(&articles, &article_id);
        if (found) {
            vector::remove(&mut articles, idx);
            blog::set_articles(&mut blog, articles);
        };
        blog
    }
}
