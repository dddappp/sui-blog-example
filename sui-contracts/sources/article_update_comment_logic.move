module sui_blog_example::article_update_comment_logic {
    use std::string::String;

    use sui::tx_context::TxContext;

    use sui_blog_example::article;
    use sui_blog_example::comment;
    use sui_blog_example::comment_updated;

    friend sui_blog_example::article_aggregate;

    public(friend) fun verify(
        comment_seq_id: u64,
        commenter: String,
        body: String,
        article: &article::Article,
        ctx: &TxContext,
    ): article::CommentUpdated {
        let _ = ctx;
        article::new_comment_updated(
            article,
            comment_seq_id,
            commenter,
            body,
        )
    }

    public(friend) fun mutate(
        comment_updated: &article::CommentUpdated,
        article: article::Article,
        ctx: &TxContext, // modify the reference to mutable if needed
    ): article::Article {
        let comment_seq_id = comment_updated::comment_seq_id(comment_updated);
        let commenter = comment_updated::commenter(comment_updated);
        let body = comment_updated::body(comment_updated);
        let id = article::id(&article);
        let _ = ctx;
        let _ = id;
        let comment = article::borrow_mut_comment(&mut article, comment_seq_id);
        comment::set_commenter(comment, commenter);
        comment::set_body(comment, body);
        article
    }
}
