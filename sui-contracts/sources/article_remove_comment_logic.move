module SuiBlogExample::article_remove_comment_logic {
    use sui::tx_context::TxContext;

    use SuiBlogExample::article;
    use SuiBlogExample::comment_removed;

    friend SuiBlogExample::article_aggregate;

    public(friend) fun verify(
        comment_seq_id: u64,
        article: &article::Article,
        ctx: &TxContext,
    ): article::CommentRemoved {
        let _ = ctx;
        article::new_comment_removed(
            article,
            comment_seq_id,
        )
    }

    public(friend) fun mutate(
        comment_removed: &article::CommentRemoved,
        article: article::Article,
        ctx: &TxContext, // modify the reference to mutable if needed
    ): article::Article {
        let comment_seq_id = comment_removed::comment_seq_id(comment_removed);
        let id = article::id(&article);
        let _ = ctx;
        let _ = id;
        article::remove_comment(&mut article, comment_seq_id);
        article
    }
}
