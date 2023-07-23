module sui_blog_example::article_create_logic {
    use std::string::String;
    use sui::tx_context::TxContext;
    use sui_blog_example::blog_aggregate;
    use sui_blog_example::article;
    use sui_blog_example::article_created;
    use sui_blog_example::blog::{Self, Blog};

    friend sui_blog_example::article_aggregate;

    public(friend) fun verify(
        blog: &mut Blog,
        title: String,
        body: String,
        owner: address,
        ctx: &mut TxContext,
    ): article::ArticleCreated {
        let _ = ctx;
        let blog_id = blog::id(blog);
        article::new_article_created(
            blog_id,
            title,
            body,
            owner,
        )
    }

    public(friend) fun mutate(
        article_created: &article::ArticleCreated,
        blog: &mut Blog,
        ctx: &mut TxContext,
    ): article::Article {
        let blog_id = article_created::blog_id(article_created);
        let title = article_created::title(article_created);
        let body = article_created::body(article_created);
        let owner = article_created::owner(article_created);
        let _ = blog_id;
        let article = article::new_article(
            title,
            body,
            owner,
            ctx,
        );
        blog_aggregate::add_article(
            blog,
            article::id(&article),
            ctx,
        );
        article
    }

}
