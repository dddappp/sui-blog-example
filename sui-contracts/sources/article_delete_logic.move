module sui_blog_example::article_delete_logic {
    use sui::tx_context::TxContext;
    use sui_blog_example::article;

    friend sui_blog_example::article_aggregate;

    public(friend) fun verify(
        article: &article::Article,
        ctx: &TxContext,
    ): article::ArticleDeleted {
        let _ = ctx;
        article::new_article_deleted(
            article,
        )
    }

    public(friend) fun mutate(
        article_deleted: &article::ArticleDeleted,
        article: article::Article,
        ctx: &TxContext, // modify the reference to mutable if needed
    ): article::Article {
        let id = article::id(&article);
        let _ = ctx;
        let _ = id;
        let _ = article_deleted;
        article
    }

}
