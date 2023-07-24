// <autogenerated>
//   This file was generated by dddappp code generator.
//   Any changes made to this file manually will be lost next time the file is regenerated.
// </autogenerated>

module sui_blog_example::blog_aggregate {
    use std::string::String;
    use sui::balance::Balance;
    use sui::object::ID;
    use sui::sui::SUI;
    use sui::tx_context;
    use sui_blog_example::blog;
    use sui_blog_example::blog_add_article_logic;
    use sui_blog_example::blog_donate_logic;
    use sui_blog_example::blog_remove_article_logic;
    use sui_blog_example::blog_update_logic;

    friend sui_blog_example::article_create_logic;
    friend sui_blog_example::article_delete_logic;

    public fun donate(
        blog: blog::Blog,
        amount: Balance<SUI>,
        ctx: &mut tx_context::TxContext,
    ) {
        let donation_received = blog_donate_logic::verify(
            &amount,
            &blog,
            ctx,
        );
        let updated_blog = blog_donate_logic::mutate(
            &donation_received,
            amount,
            blog,
            ctx,
        );
        blog::update_version_and_transfer_object(updated_blog, tx_context::sender(ctx));
        blog::emit_donation_received(donation_received);
    }

    public(friend) fun add_article(
        blog: &mut blog::Blog,
        article_id: ID,
        ctx: &mut tx_context::TxContext,
    ) {
        let article_added_to_blog = blog_add_article_logic::verify(
            article_id,
            blog,
            ctx,
        );
        blog_add_article_logic::mutate(
            &article_added_to_blog,
            blog,
            ctx,
        );
        blog::update_object_version(blog);
        blog::emit_article_added_to_blog(article_added_to_blog);
    }

    public(friend) fun remove_article(
        blog: &mut blog::Blog,
        article_id: ID,
        ctx: &mut tx_context::TxContext,
    ) {
        let article_removed_from_blog = blog_remove_article_logic::verify(
            article_id,
            blog,
            ctx,
        );
        blog_remove_article_logic::mutate(
            &article_removed_from_blog,
            blog,
            ctx,
        );
        blog::update_object_version(blog);
        blog::emit_article_removed_from_blog(article_removed_from_blog);
    }

    public entry fun update(
        blog: blog::Blog,
        name: String,
        articles: vector<ID>,
        ctx: &mut tx_context::TxContext,
    ) {
        let blog_updated = blog_update_logic::verify(
            name,
            articles,
            &blog,
            ctx,
        );
        let updated_blog = blog_update_logic::mutate(
            &blog_updated,
            blog,
            ctx,
        );
        blog::update_version_and_transfer_object(updated_blog, tx_context::sender(ctx));
        blog::emit_blog_updated(blog_updated);
    }

}
