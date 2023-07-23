// <autogenerated>
//   This file was generated by dddappp code generator.
//   Any changes made to this file manually will be lost next time the file is regenerated.
// </autogenerated>

module sui_blog_example::article_aggregate {
    use std::string::String;
    use sui::object::ID;
    use sui::tx_context;
    use sui_blog_example::article;
    use sui_blog_example::article_add_comment_logic;
    use sui_blog_example::article_create_logic;
    use sui_blog_example::article_delete_logic;
    use sui_blog_example::article_remove_comment_logic;
    use sui_blog_example::article_update_comment_logic;
    use sui_blog_example::article_update_logic;
    use sui_blog_example::article_update_tags_logic;
    use sui_blog_example::article_update_tags_v2_logic;
    use sui_blog_example::blog::Blog;
    use sui_blog_example::tag::Tag;
    use sui_blog_example::tag_v2::TagV2;

    public entry fun create(
        blog: &mut Blog,
        title: String,
        body: String,
        owner: address,
        ctx: &mut tx_context::TxContext,
    ) {
        let article_created = article_create_logic::verify(
            blog,
            title,
            body,
            owner,
            ctx,
        );
        let article = article_create_logic::mutate(
            &article_created,
            blog,
            ctx,
        );
        article::set_article_created_id(&mut article_created, article::id(&article));
        article::transfer_object(article, tx_context::sender(ctx));
        article::emit_article_created(article_created);
    }

    public entry fun update(
        article: article::Article,
        title: String,
        body: String,
        owner: address,
        tags: vector<String>,
        tags_v2: vector<ID>,
        ctx: &mut tx_context::TxContext,
    ) {
        let article_updated = article_update_logic::verify(
            title,
            body,
            owner,
            tags,
            tags_v2,
            &article,
            ctx,
        );
        let updated_article = article_update_logic::mutate(
            &article_updated,
            article,
            ctx,
        );
        article::update_version_and_transfer_object(updated_article, tx_context::sender(ctx));
        article::emit_article_updated(article_updated);
    }

    public entry fun delete(
        article: article::Article,
        blog: &mut Blog,
        ctx: &mut tx_context::TxContext,
    ) {
        let article_deleted = article_delete_logic::verify(
            blog,
            &article,
            ctx,
        );
        let updated_article = article_delete_logic::mutate(
            &article_deleted,
            blog,
            article,
            ctx,
        );
        article::drop_article(updated_article);
        article::emit_article_deleted(article_deleted);
    }

    public entry fun update_comment(
        article: article::Article,
        comment_seq_id: u64,
        commenter: String,
        body: String,
        owner: address,
        ctx: &mut tx_context::TxContext,
    ) {
        let comment_updated = article_update_comment_logic::verify(
            comment_seq_id,
            commenter,
            body,
            owner,
            &article,
            ctx,
        );
        let updated_article = article_update_comment_logic::mutate(
            &comment_updated,
            article,
            ctx,
        );
        article::update_version_and_transfer_object(updated_article, tx_context::sender(ctx));
        article::emit_comment_updated(comment_updated);
    }

    public entry fun remove_comment(
        article: article::Article,
        comment_seq_id: u64,
        ctx: &mut tx_context::TxContext,
    ) {
        let comment_removed = article_remove_comment_logic::verify(
            comment_seq_id,
            &article,
            ctx,
        );
        let updated_article = article_remove_comment_logic::mutate(
            &comment_removed,
            article,
            ctx,
        );
        article::update_version_and_transfer_object(updated_article, tx_context::sender(ctx));
        article::emit_comment_removed(comment_removed);
    }

    public entry fun add_comment(
        article: article::Article,
        commenter: String,
        body: String,
        ctx: &mut tx_context::TxContext,
    ) {
        let comment_added = article_add_comment_logic::verify(
            commenter,
            body,
            &article,
            ctx,
        );
        let updated_article = article_add_comment_logic::mutate(
            &comment_added,
            article,
            ctx,
        );
        article::update_version_and_transfer_object(updated_article, tx_context::sender(ctx));
        article::emit_comment_added(comment_added);
    }

    public entry fun update_tags(
        article: article::Article,
        tags_0: &Tag,
        tags_1: &Tag,
        tags_2: &Tag,
        ctx: &mut tx_context::TxContext,
    ) {
        let article_tags_updated = article_update_tags_logic::verify(
            tags_0,
            tags_1,
            tags_2,
            &article,
            ctx,
        );
        let updated_article = article_update_tags_logic::mutate(
            &article_tags_updated,
            article,
            ctx,
        );
        article::update_version_and_transfer_object(updated_article, tx_context::sender(ctx));
        article::emit_article_tags_updated(article_tags_updated);
    }

    public entry fun update_tags_v2(
        article: article::Article,
        tags_0: &TagV2,
        tags_1: &TagV2,
        tags_2: &TagV2,
        ctx: &mut tx_context::TxContext,
    ) {
        let article_tags_v2_updated = article_update_tags_v2_logic::verify(
            tags_0,
            tags_1,
            tags_2,
            &article,
            ctx,
        );
        let updated_article = article_update_tags_v2_logic::mutate(
            &article_tags_v2_updated,
            article,
            ctx,
        );
        article::update_version_and_transfer_object(updated_article, tx_context::sender(ctx));
        article::emit_article_tags_v2_updated(article_tags_v2_updated);
    }

}
