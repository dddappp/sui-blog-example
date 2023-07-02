module sui_blog_example::article_update_logic {
    use std::string::String;
    use sui::tx_context::TxContext;
    use sui_blog_example::article;
    use sui_blog_example::article_updated;

    friend sui_blog_example::article_aggregate;

    public(friend) fun verify(
        title: String,
        body: String,
        owner: address,
        article: &article::Article,
        ctx: &TxContext,
    ): article::ArticleUpdated {
        let _ = ctx;
        assert!(sui::tx_context::sender(ctx) == article::owner(article), 111);
        article::new_article_updated(
            article,
            title,
            body,
            owner,
        )
    }

    public(friend) fun mutate(
        article_updated: &article::ArticleUpdated,
        article: article::Article,
        ctx: &TxContext, // modify the reference to mutable if needed
    ): article::Article {
        let title = article_updated::title(article_updated);
        let body = article_updated::body(article_updated);
        let owner = article_updated::owner(article_updated);
        let id = article::id(&article);
        let _ = ctx;
        let _ = id;
        article::set_title(&mut article, title);
        article::set_body(&mut article, body);
        article::set_owner(&mut article, owner);
        article
    }

}
