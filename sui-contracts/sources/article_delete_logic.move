module sui_blog_example::article_delete_logic {
    use sui::tx_context::TxContext;
    use sui_blog_example::article_deleted;
    use sui_blog_example::article;
    use sui_blog_example::blog::{Self, Blog};

    friend sui_blog_example::article_aggregate;

    public(friend) fun verify(
        blog: &mut Blog,
        article: &article::Article,
        ctx: &TxContext,
    ): article::ArticleDeleted {
        let _ = ctx;
        article::new_article_deleted(
            article,
            blog::id(blog),
        )
    }

    public(friend) fun mutate(
        article_deleted: &article::ArticleDeleted,
        blog: &mut Blog,
        article: article::Article,
        ctx: &TxContext, // modify the reference to mutable if needed
    ): article::Article {
        let _blog_id = article_deleted::blog_id(article_deleted);
        let id = article::id(&article);
        let _ = ctx;
        let _ = id;
        let _ = article_deleted;
        let _ = blog;
        article
    }

}
