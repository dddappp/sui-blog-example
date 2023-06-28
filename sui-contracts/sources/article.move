// <autogenerated>
//   This file was generated by dddappp code generator.
//   Any changes made to this file manually will be lost next time the file is regenerated.
// </autogenerated>

module sui_blog_example::article {
    use std::option;
    use std::string::String;
    use sui::event;
    use sui::object::{Self, UID};
    use sui::table;
    use sui::transfer;
    use sui::tx_context::TxContext;
    use sui_blog_example::comment::{Self, Comment};
    friend sui_blog_example::article_create_logic;
    friend sui_blog_example::article_update_logic;
    friend sui_blog_example::article_delete_logic;
    friend sui_blog_example::article_add_comment_logic;
    friend sui_blog_example::article_remove_comment_logic;
    friend sui_blog_example::article_update_comment_logic;
    friend sui_blog_example::article_aggregate;

    const EID_DATA_TOO_LONG: u64 = 102;
    const EINAPPROPRIATE_VERSION: u64 = 103;

    struct Article has key {
        id: UID,
        version: u64,
        title: String,
        body: String,
        comments: table::Table<u64, Comment>,
    }

    public fun id(article: &Article): object::ID {
        object::uid_to_inner(&article.id)
    }

    public fun version(article: &Article): u64 {
        article.version
    }

    public fun title(article: &Article): String {
        article.title
    }

    public(friend) fun set_title(article: &mut Article, title: String) {
        article.title = title;
    }

    public fun body(article: &Article): String {
        article.body
    }

    public(friend) fun set_body(article: &mut Article, body: String) {
        article.body = body;
    }

    public(friend) fun add_comment(article: &mut Article, comment: Comment) {
        let key = comment::comment_seq_id(&comment);
        table::add(&mut article.comments, key, comment);
    }

    public(friend) fun remove_comment(article: &mut Article, comment_seq_id: u64) {
        let comment = table::remove(&mut article.comments, comment_seq_id);
        comment::drop_comment(comment);
    }

    public(friend) fun borrow_mut_comment(article: &mut Article, comment_seq_id: u64): &mut Comment {
        table::borrow_mut(&mut article.comments, comment_seq_id)
    }

    public fun borrow_comment(article: &Article, comment_seq_id: u64): &Comment {
        table::borrow(&article.comments, comment_seq_id)
    }

    public fun comments_contains(article: &Article, comment_seq_id: u64): bool {
        table::contains(&article.comments, comment_seq_id)
    }

    public fun comments_length(article: &Article): u64 {
        table::length(&article.comments)
    }

    public(friend) fun new_article(
        title: String,
        body: String,
        ctx: &mut TxContext,
    ): Article {
        assert!(std::string::length(&title) <= 200, EID_DATA_TOO_LONG);
        assert!(std::string::length(&body) <= 2000, EID_DATA_TOO_LONG);
        Article {
            id: object::new(ctx),
            version: 0,
            title,
            body,
            comments: table::new<u64, Comment>(ctx),
        }
    }

    struct ArticleCreated has copy, drop {
        id: option::Option<object::ID>,
        title: String,
        body: String,
    }

    public fun article_created_id(article_created: &ArticleCreated): option::Option<object::ID> {
        article_created.id
    }

    public(friend) fun set_article_created_id(article_created: &mut ArticleCreated, id: object::ID) {
        article_created.id = option::some(id);
    }

    public fun article_created_title(article_created: &ArticleCreated): String {
        article_created.title
    }

    public fun article_created_body(article_created: &ArticleCreated): String {
        article_created.body
    }

    public(friend) fun new_article_created(
        title: String,
        body: String,
    ): ArticleCreated {
        ArticleCreated {
            id: option::none(),
            title,
            body,
        }
    }

    struct ArticleUpdated has copy, drop {
        id: object::ID,
        version: u64,
        title: String,
        body: String,
    }

    public fun article_updated_id(article_updated: &ArticleUpdated): object::ID {
        article_updated.id
    }

    public fun article_updated_title(article_updated: &ArticleUpdated): String {
        article_updated.title
    }

    public fun article_updated_body(article_updated: &ArticleUpdated): String {
        article_updated.body
    }

    public(friend) fun new_article_updated(
        article: &Article,
        title: String,
        body: String,
    ): ArticleUpdated {
        ArticleUpdated {
            id: id(article),
            version: version(article),
            title,
            body,
        }
    }

    struct ArticleDeleted has copy, drop {
        id: object::ID,
        version: u64,
    }

    public fun article_deleted_id(article_deleted: &ArticleDeleted): object::ID {
        article_deleted.id
    }

    public(friend) fun new_article_deleted(
        article: &Article,
    ): ArticleDeleted {
        ArticleDeleted {
            id: id(article),
            version: version(article),
        }
    }

    struct CommentAdded has copy, drop {
        id: object::ID,
        version: u64,
        comment_seq_id: u64,
        commenter: String,
        body: String,
    }

    public fun comment_added_id(comment_added: &CommentAdded): object::ID {
        comment_added.id
    }

    public fun comment_added_comment_seq_id(comment_added: &CommentAdded): u64 {
        comment_added.comment_seq_id
    }

    public fun comment_added_commenter(comment_added: &CommentAdded): String {
        comment_added.commenter
    }

    public fun comment_added_body(comment_added: &CommentAdded): String {
        comment_added.body
    }

    public(friend) fun new_comment_added(
        article: &Article,
        comment_seq_id: u64,
        commenter: String,
        body: String,
    ): CommentAdded {
        CommentAdded {
            id: id(article),
            version: version(article),
            comment_seq_id,
            commenter,
            body,
        }
    }

    struct CommentRemoved has copy, drop {
        id: object::ID,
        version: u64,
        comment_seq_id: u64,
    }

    public fun comment_removed_id(comment_removed: &CommentRemoved): object::ID {
        comment_removed.id
    }

    public fun comment_removed_comment_seq_id(comment_removed: &CommentRemoved): u64 {
        comment_removed.comment_seq_id
    }

    public(friend) fun new_comment_removed(
        article: &Article,
        comment_seq_id: u64,
    ): CommentRemoved {
        CommentRemoved {
            id: id(article),
            version: version(article),
            comment_seq_id,
        }
    }

    struct CommentUpdated has copy, drop {
        id: object::ID,
        version: u64,
        comment_seq_id: u64,
        commenter: String,
        body: String,
    }

    public fun comment_updated_id(comment_updated: &CommentUpdated): object::ID {
        comment_updated.id
    }

    public fun comment_updated_comment_seq_id(comment_updated: &CommentUpdated): u64 {
        comment_updated.comment_seq_id
    }

    public fun comment_updated_commenter(comment_updated: &CommentUpdated): String {
        comment_updated.commenter
    }

    public fun comment_updated_body(comment_updated: &CommentUpdated): String {
        comment_updated.body
    }

    public(friend) fun new_comment_updated(
        article: &Article,
        comment_seq_id: u64,
        commenter: String,
        body: String,
    ): CommentUpdated {
        CommentUpdated {
            id: id(article),
            version: version(article),
            comment_seq_id,
            commenter,
            body,
        }
    }


    public(friend) fun transfer_object(article: Article, recipient: address) {
        assert!(article.version == 0, EINAPPROPRIATE_VERSION);
        transfer::transfer(article, recipient);
    }

    public(friend) fun update_version_and_transfer_object(article: Article, recipient: address) {
        update_object_version(&mut article);
        transfer::transfer(article, recipient);
    }

    public(friend) fun share_object(article: Article) {
        assert!(article.version == 0, EINAPPROPRIATE_VERSION);
        transfer::share_object(article);
    }

    public(friend) fun update_version_and_share_object(article: Article) {
        update_object_version(&mut article);
        transfer::share_object(article);
    }

    public(friend) fun freeze_object(article: Article) {
        assert!(article.version == 0, EINAPPROPRIATE_VERSION);
        transfer::freeze_object(article);
    }

    public(friend) fun update_version_and_freeze_object(article: Article) {
        update_object_version(&mut article);
        transfer::freeze_object(article);
    }

    fun update_object_version(article: &mut Article) {
        article.version = article.version + 1;
        //assert!(article.version != 0, EINAPPROPRIATE_VERSION);
    }

    public(friend) fun drop_article(article: Article) {
        let Article {
            id,
            version: _version,
            title: _title,
            body: _body,
            comments,
        } = article;
        object::delete(id);
        table::destroy_empty(comments);
    }

    public(friend) fun emit_article_created(article_created: ArticleCreated) {
        event::emit(article_created);
    }

    public(friend) fun emit_article_updated(article_updated: ArticleUpdated) {
        event::emit(article_updated);
    }

    public(friend) fun emit_article_deleted(article_deleted: ArticleDeleted) {
        event::emit(article_deleted);
    }

    public(friend) fun emit_comment_added(comment_added: CommentAdded) {
        event::emit(comment_added);
    }

    public(friend) fun emit_comment_removed(comment_removed: CommentRemoved) {
        event::emit(comment_removed);
    }

    public(friend) fun emit_comment_updated(comment_updated: CommentUpdated) {
        event::emit(comment_updated);
    }

}