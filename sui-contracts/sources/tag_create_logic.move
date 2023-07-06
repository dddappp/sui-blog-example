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
        let _ = tag_name_table;
        tag::new_tag_created(
            name,
        )
    }

    public(friend) fun mutate(
        tag_created: &tag::TagCreated,
        tag_name_table: &mut tag::TagNameTable,
        ctx: &mut TxContext,
    ): tag::Tag {
        tag::create_tag(
            tag_created::name(tag_created),
            tag_name_table,
            ctx,
        )
    }

}
