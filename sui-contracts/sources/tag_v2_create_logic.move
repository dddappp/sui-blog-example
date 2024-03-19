#[allow(unused_mut_parameter)]
module sui_blog_example::tag_v2_create_logic {
    use std::string::String;
    use sui::tx_context::TxContext;
    use sui_blog_example::tag_v2;
    use sui_blog_example::tag_v2_created;

    friend sui_blog_example::tag_v2_aggregate;

    public(friend) fun verify(
        name: String,
        ctx: &mut TxContext,
    ): tag_v2::TagV2Created {
        let _ = ctx;
        tag_v2::new_tag_v2_created(
            name,
        )
    }

    public(friend) fun mutate(
        tag_v2_created: &tag_v2::TagV2Created,
        ctx: &mut TxContext,
    ): tag_v2::TagV2 {
        let name = tag_v2_created::name(tag_v2_created);
        tag_v2::new_tag_v2(
            name,
            ctx,
        )
    }

}
