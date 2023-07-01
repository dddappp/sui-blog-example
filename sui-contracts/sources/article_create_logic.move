module sui_blog_example::article_create_logic {
    use std::string::String;
    use sui::tx_context::TxContext;
    use sui_blog_example::article;
    use sui_blog_example::article_created;

    friend sui_blog_example::article_aggregate;

    public(friend) fun verify(
        title: String,
        body: String,
        ctx: &mut TxContext,
    ): article::ArticleCreated {
        let _ = ctx;
        article::new_article_created(
            title,
            body,
        )
    }

    public(friend) fun mutate(
        article_created: &article::ArticleCreated,
        ctx: &mut TxContext,
    ): article::Article {
        let title = article_created::title(article_created);
        let body = article_created::body(article_created);
        article::new_article(
            title,
            body,
            ctx,
        )
    }

}
