// <autogenerated>
//   This file was generated by dddappp code generator.
//   Any changes made to this file manually will be lost next time the file is regenerated.
// </autogenerated>

module sui_blog_example::blog {
    use std::string::String;
    use sui::balance::Balance;
    use sui::event;
    use sui::object::{Self, ID, UID};
    use sui::sui::SUI;
    use sui::transfer;
    use sui::tx_context::TxContext;

    struct BLOG has drop {}

    friend sui_blog_example::blog_donate_logic;
    friend sui_blog_example::blog_add_article_logic;
    friend sui_blog_example::blog_remove_article_logic;
    friend sui_blog_example::blog_update_logic;
    friend sui_blog_example::blog_aggregate;

    const EDATA_TOO_LONG: u64 = 102;
    const EINAPPROPRIATE_VERSION: u64 = 103;

    fun init(witness: BLOG, ctx: &mut TxContext) {
        let blog = new_blog(
            witness,
            ctx,
        );
        share_object(blog);
    }

    struct Blog has key {
        id: UID,
        version: u64,
        name: String,
        articles: vector<ID>,
        vault: Balance<SUI>,
    }

    public fun id(blog: &Blog): object::ID {
        object::uid_to_inner(&blog.id)
    }

    public fun version(blog: &Blog): u64 {
        blog.version
    }

    public fun name(blog: &Blog): String {
        blog.name
    }

    public(friend) fun set_name(blog: &mut Blog, name: String) {
        assert!(std::string::length(&name) <= 200, EDATA_TOO_LONG);
        blog.name = name;
    }

    public fun articles(blog: &Blog): vector<ID> {
        blog.articles
    }

    public(friend) fun set_articles(blog: &mut Blog, articles: vector<ID>) {
        blog.articles = articles;
    }

    public(friend) fun borrow_vault(blog: &Blog): &Balance<SUI> {
        &blog.vault
    }

    public(friend) fun borrow_mut_vault(blog: &mut Blog): &mut Balance<SUI> {
        &mut blog.vault
    }

    public(friend) fun new_blog(
        _witness: BLOG,
        ctx: &mut TxContext,
    ): Blog {
        Blog {
            id: object::new(ctx),
            version: 0,
            name: std::string::utf8(b"Unnamed Blog"),
            articles: std::vector::empty(),
            vault: sui::balance::zero(),
        }
    }

    struct DonationReceived has copy, drop {
        id: object::ID,
        version: u64,
        amount: u64,
    }

    public fun donation_received_id(donation_received: &DonationReceived): object::ID {
        donation_received.id
    }

    public fun donation_received_amount(donation_received: &DonationReceived): u64 {
        donation_received.amount
    }

    public(friend) fun new_donation_received(
        blog: &Blog,
        amount: u64,
    ): DonationReceived {
        DonationReceived {
            id: id(blog),
            version: version(blog),
            amount,
        }
    }

    struct ArticleAddedToBlog has copy, drop {
        id: object::ID,
        version: u64,
        article_id: ID,
    }

    public fun article_added_to_blog_id(article_added_to_blog: &ArticleAddedToBlog): object::ID {
        article_added_to_blog.id
    }

    public fun article_added_to_blog_article_id(article_added_to_blog: &ArticleAddedToBlog): ID {
        article_added_to_blog.article_id
    }

    public(friend) fun new_article_added_to_blog(
        blog: &Blog,
        article_id: ID,
    ): ArticleAddedToBlog {
        ArticleAddedToBlog {
            id: id(blog),
            version: version(blog),
            article_id,
        }
    }

    struct ArticleRemovedFromBlog has copy, drop {
        id: object::ID,
        version: u64,
        article_id: ID,
    }

    public fun article_removed_from_blog_id(article_removed_from_blog: &ArticleRemovedFromBlog): object::ID {
        article_removed_from_blog.id
    }

    public fun article_removed_from_blog_article_id(article_removed_from_blog: &ArticleRemovedFromBlog): ID {
        article_removed_from_blog.article_id
    }

    public(friend) fun new_article_removed_from_blog(
        blog: &Blog,
        article_id: ID,
    ): ArticleRemovedFromBlog {
        ArticleRemovedFromBlog {
            id: id(blog),
            version: version(blog),
            article_id,
        }
    }

    struct BlogUpdated has copy, drop {
        id: object::ID,
        version: u64,
        name: String,
        articles: vector<ID>,
    }

    public fun blog_updated_id(blog_updated: &BlogUpdated): object::ID {
        blog_updated.id
    }

    public fun blog_updated_name(blog_updated: &BlogUpdated): String {
        blog_updated.name
    }

    public fun blog_updated_articles(blog_updated: &BlogUpdated): vector<ID> {
        blog_updated.articles
    }

    public(friend) fun new_blog_updated(
        blog: &Blog,
        name: String,
        articles: vector<ID>,
    ): BlogUpdated {
        BlogUpdated {
            id: id(blog),
            version: version(blog),
            name,
            articles,
        }
    }


    public(friend) fun transfer_object(blog: Blog, recipient: address) {
        assert!(blog.version == 0, EINAPPROPRIATE_VERSION);
        transfer::transfer(blog, recipient);
    }

    public(friend) fun update_version_and_transfer_object(blog: Blog, recipient: address) {
        update_object_version(&mut blog);
        transfer::transfer(blog, recipient);
    }

    public(friend) fun share_object(blog: Blog) {
        assert!(blog.version == 0, EINAPPROPRIATE_VERSION);
        transfer::share_object(blog);
    }

    public(friend) fun update_version_and_share_object(blog: Blog) {
        update_object_version(&mut blog);
        transfer::share_object(blog);
    }

    public(friend) fun freeze_object(blog: Blog) {
        assert!(blog.version == 0, EINAPPROPRIATE_VERSION);
        transfer::freeze_object(blog);
    }

    public(friend) fun update_version_and_freeze_object(blog: Blog) {
        update_object_version(&mut blog);
        transfer::freeze_object(blog);
    }

    public(friend) fun update_object_version(blog: &mut Blog) {
        blog.version = blog.version + 1;
        //assert!(blog.version != 0, EINAPPROPRIATE_VERSION);
    }

    public(friend) fun drop_blog(blog: Blog) {
        let Blog {
            id,
            version: _version,
            name: _name,
            articles: _articles,
            vault,
        } = blog;
        object::delete(id);
        sui::balance::destroy_zero(vault);
    }

    public(friend) fun emit_donation_received(donation_received: DonationReceived) {
        event::emit(donation_received);
    }

    public(friend) fun emit_article_added_to_blog(article_added_to_blog: ArticleAddedToBlog) {
        event::emit(article_added_to_blog);
    }

    public(friend) fun emit_article_removed_from_blog(article_removed_from_blog: ArticleRemovedFromBlog) {
        event::emit(article_removed_from_blog);
    }

    public(friend) fun emit_blog_updated(blog_updated: BlogUpdated) {
        event::emit(blog_updated);
    }

}
