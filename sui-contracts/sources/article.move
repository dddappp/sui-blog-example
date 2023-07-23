// <autogenerated>
//   This file was generated by dddappp code generator.
//   Any changes made to this file manually will be lost next time the file is regenerated.
// </autogenerated>

module sui_blog_example::article {
    use std::option;
    use std::string::String;
    use sui::event;
    use sui::object::{Self, ID, UID};
    use sui::table;
    use sui::transfer;
    use sui::tx_context::TxContext;
    use sui_blog_example::comment::{Self, Comment};
    friend sui_blog_example::article_create_logic;
    friend sui_blog_example::article_update_logic;
    friend sui_blog_example::article_delete_logic;
    friend sui_blog_example::article_update_comment_logic;
    friend sui_blog_example::article_remove_comment_logic;
    friend sui_blog_example::article_add_comment_logic;
    friend sui_blog_example::article_update_tags_logic;
    friend sui_blog_example::article_update_tags_v2_logic;
    friend sui_blog_example::article_aggregate;

    const EID_ALREADY_EXISTS: u64 = 101;
    const EDATA_TOO_LONG: u64 = 102;
    const EINAPPROPRIATE_VERSION: u64 = 103;
    const EID_NOT_FOUND: u64 = 106;

    struct Article has key {
        id: UID,
        version: u64,
        title: String,
        body: String,
        owner: address,
        tags: vector<String>,
        tags_v2: vector<ID>,
        comments: table::Table<u64, Comment>,
        comment_seq_id_generator: CommentSeqIdGenerator,
    }

    struct CommentSeqIdGenerator has store {
        sequence: u64,
    }

    public(friend) fun current_comment_seq_id(article: &Article): u64 {
        article.comment_seq_id_generator.sequence
    }

    public(friend) fun next_comment_seq_id(article: &mut Article): u64 {
        article.comment_seq_id_generator.sequence = article.comment_seq_id_generator.sequence + 1;
        article.comment_seq_id_generator.sequence
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
        assert!(std::string::length(&title) <= 200, EDATA_TOO_LONG);
        article.title = title;
    }

    public fun body(article: &Article): String {
        article.body
    }

    public(friend) fun set_body(article: &mut Article, body: String) {
        assert!(std::string::length(&body) <= 2000, EDATA_TOO_LONG);
        article.body = body;
    }

    public fun owner(article: &Article): address {
        article.owner
    }

    public(friend) fun set_owner(article: &mut Article, owner: address) {
        article.owner = owner;
    }

    public fun tags(article: &Article): vector<String> {
        article.tags
    }

    public(friend) fun set_tags(article: &mut Article, tags: vector<String>) {
        article.tags = tags;
    }

    public fun tags_v2(article: &Article): vector<ID> {
        article.tags_v2
    }

    public(friend) fun set_tags_v2(article: &mut Article, tags_v2: vector<ID>) {
        article.tags_v2 = tags_v2;
    }

    public(friend) fun add_comment(article: &mut Article, comment: Comment) {
        let key = comment::comment_seq_id(&comment);
        assert!(!table::contains(&article.comments, key), EID_ALREADY_EXISTS);
        table::add(&mut article.comments, key, comment);
    }

    public(friend) fun remove_comment(article: &mut Article, comment_seq_id: u64) {
        assert!(table::contains(&article.comments, comment_seq_id), EID_NOT_FOUND);
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
        owner: address,
        ctx: &mut TxContext,
    ): Article {
        assert!(std::string::length(&title) <= 200, EDATA_TOO_LONG);
        assert!(std::string::length(&body) <= 2000, EDATA_TOO_LONG);
        Article {
            id: object::new(ctx),
            version: 0,
            title,
            body,
            owner,
            tags: std::vector::empty(),
            tags_v2: std::vector::empty(),
            comments: table::new<u64, Comment>(ctx),
            comment_seq_id_generator: CommentSeqIdGenerator { sequence: 0, },
        }
    }

    struct ArticleCreated has copy, drop {
        id: option::Option<object::ID>,
        title: String,
        body: String,
        owner: address,
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

    public fun article_created_owner(article_created: &ArticleCreated): address {
        article_created.owner
    }

    public(friend) fun new_article_created(
        title: String,
        body: String,
        owner: address,
    ): ArticleCreated {
        ArticleCreated {
            id: option::none(),
            title,
            body,
            owner,
        }
    }

    struct ArticleUpdated has copy, drop {
        id: object::ID,
        version: u64,
        title: String,
        body: String,
        owner: address,
        tags: vector<String>,
        tags_v2: vector<ID>,
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

    public fun article_updated_owner(article_updated: &ArticleUpdated): address {
        article_updated.owner
    }

    public fun article_updated_tags(article_updated: &ArticleUpdated): vector<String> {
        article_updated.tags
    }

    public fun article_updated_tags_v2(article_updated: &ArticleUpdated): vector<ID> {
        article_updated.tags_v2
    }

    public(friend) fun new_article_updated(
        article: &Article,
        title: String,
        body: String,
        owner: address,
        tags: vector<String>,
        tags_v2: vector<ID>,
    ): ArticleUpdated {
        ArticleUpdated {
            id: id(article),
            version: version(article),
            title,
            body,
            owner,
            tags,
            tags_v2,
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

    struct CommentUpdated has copy, drop {
        id: object::ID,
        version: u64,
        comment_seq_id: u64,
        commenter: String,
        body: String,
        owner: address,
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

    public fun comment_updated_owner(comment_updated: &CommentUpdated): address {
        comment_updated.owner
    }

    public(friend) fun new_comment_updated(
        article: &Article,
        comment_seq_id: u64,
        commenter: String,
        body: String,
        owner: address,
    ): CommentUpdated {
        CommentUpdated {
            id: id(article),
            version: version(article),
            comment_seq_id,
            commenter,
            body,
            owner,
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

    struct CommentAdded has copy, drop {
        id: object::ID,
        version: u64,
        comment_seq_id: u64,
        commenter: String,
        body: String,
        owner: address,
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

    public fun comment_added_owner(comment_added: &CommentAdded): address {
        comment_added.owner
    }

    public(friend) fun new_comment_added(
        article: &Article,
        comment_seq_id: u64,
        commenter: String,
        body: String,
        owner: address,
    ): CommentAdded {
        CommentAdded {
            id: id(article),
            version: version(article),
            comment_seq_id,
            commenter,
            body,
            owner,
        }
    }

    struct ArticleTagsUpdated has copy, drop {
        id: object::ID,
        version: u64,
        tags: vector<String>,
    }

    public fun article_tags_updated_id(article_tags_updated: &ArticleTagsUpdated): object::ID {
        article_tags_updated.id
    }

    public fun article_tags_updated_tags(article_tags_updated: &ArticleTagsUpdated): vector<String> {
        article_tags_updated.tags
    }

    public(friend) fun new_article_tags_updated(
        article: &Article,
        tags: vector<String>,
    ): ArticleTagsUpdated {
        ArticleTagsUpdated {
            id: id(article),
            version: version(article),
            tags,
        }
    }

    struct ArticleTagsV2Updated has copy, drop {
        id: object::ID,
        version: u64,
        tags: vector<ID>,
    }

    public fun article_tags_v2_updated_id(article_tags_v2_updated: &ArticleTagsV2Updated): object::ID {
        article_tags_v2_updated.id
    }

    public fun article_tags_v2_updated_tags(article_tags_v2_updated: &ArticleTagsV2Updated): vector<ID> {
        article_tags_v2_updated.tags
    }

    public(friend) fun new_article_tags_v2_updated(
        article: &Article,
        tags: vector<ID>,
    ): ArticleTagsV2Updated {
        ArticleTagsV2Updated {
            id: id(article),
            version: version(article),
            tags,
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
            owner: _owner,
            tags: _tags,
            tags_v2: _tags_v2,
            comments,
            comment_seq_id_generator,
        } = article;
        object::delete(id);
        let CommentSeqIdGenerator {
            sequence: _,
        } = comment_seq_id_generator;
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

    public(friend) fun emit_comment_updated(comment_updated: CommentUpdated) {
        event::emit(comment_updated);
    }

    public(friend) fun emit_comment_removed(comment_removed: CommentRemoved) {
        event::emit(comment_removed);
    }

    public(friend) fun emit_comment_added(comment_added: CommentAdded) {
        event::emit(comment_added);
    }

    public(friend) fun emit_article_tags_updated(article_tags_updated: ArticleTagsUpdated) {
        event::emit(article_tags_updated);
    }

    public(friend) fun emit_article_tags_v2_updated(article_tags_v2_updated: ArticleTagsV2Updated) {
        event::emit(article_tags_v2_updated);
    }

}
