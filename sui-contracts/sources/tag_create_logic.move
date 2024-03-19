#[allow(unused_mut_parameter)]
module sui_blog_example::tag_create_logic {
    use std::string::String;
    use sui::tx_context::TxContext;
    use sui_blog_example::tag;
    use sui_blog_example::tag_created;

    friend sui_blog_example::tag_aggregate;

    public(friend) fun verify(
        name: String,
        tag_name_table: &tag::TagNameTable,
        ctx: &mut TxContext,
    ): tag::TagCreated {
        let _ = ctx;
        tag::asset_name_not_exists(name, tag_name_table);
        tag::new_tag_created(
            name,
        )
    }

    public(friend) fun mutate(
        tag_created: &tag::TagCreated,
        tag_name_table: &mut tag::TagNameTable,
        ctx: &mut TxContext,
    ): tag::Tag {
        let name = tag_created::name(tag_created);
        tag::create_tag(
            name,
            tag_name_table,
            ctx,
        )
    }

}
