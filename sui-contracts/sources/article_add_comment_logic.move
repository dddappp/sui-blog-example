module sui_blog_example::article_add_comment_logic {
    use std::string::String;
    use sui::tx_context::TxContext;
    use sui_blog_example::article;
    use sui_blog_example::comment;
    use sui_blog_example::comment_added;

    friend sui_blog_example::article_aggregate;

    public(friend) fun verify(
        commenter: String,
        body: String,
        article: &article::Article,
        ctx: &TxContext,
    ): article::CommentAdded {
        let _ = ctx;
        let comment_seq_id = article::current_comment_seq_id(article) + 1;
        article::new_comment_added(
            article,
            comment_seq_id,
            commenter,
            body,
            sui::tx_context::sender(ctx),
        )
    }

    public(friend) fun mutate(
        comment_added: &article::CommentAdded,
        article: article::Article,
        ctx: &TxContext, // modify the reference to mutable if needed
    ): article::Article {
        let comment_seq_id = article::next_comment_seq_id(&mut article);
        let commenter = comment_added::commenter(comment_added);
        let body = comment_added::body(comment_added);
        let owner = comment_added::owner(comment_added);
        let id = article::id(&article);
        let _ = ctx;
        let _ = id;
        let comment = comment::new_comment(
            comment_seq_id,
            commenter,
            body,
            owner,
        );
        article::add_comment(&mut article, comment);
        article
    }

}
