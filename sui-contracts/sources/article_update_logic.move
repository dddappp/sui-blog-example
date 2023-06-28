module SuiBlogExample::article_update_logic {
    use std::string::String;

    use sui::tx_context::TxContext;

    use SuiBlogExample::article;
    use SuiBlogExample::article_updated;

    friend SuiBlogExample::article_aggregate;

    public(friend) fun verify(
        title: String,
        body: String,
        article: &article::Article,
        ctx: &TxContext,
    ): article::ArticleUpdated {
        let _ = ctx;
        article::new_article_updated(
            article,
            title,
            body,
        )
    }

    public(friend) fun mutate(
        article_updated: &article::ArticleUpdated,
        article: article::Article,
        ctx: &TxContext, // modify the reference to mutable if needed
    ): article::Article {
        let title = article_updated::title(article_updated);
        let body = article_updated::body(article_updated);
        let id = article::id(&article);
        let _ = ctx;
        let _ = id;
        article::set_title(&mut article, title);
        article::set_body(&mut article, body);
        article
    }
}
