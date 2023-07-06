// <autogenerated>
//   This file was generated by dddappp code generator.
//   Any changes made to this file manually will be lost next time the file is regenerated.
// </autogenerated>

module sui_blog_example::tag_aggregate {
    use std::string::String;
    use sui::tx_context;
    use sui_blog_example::tag;
    use sui_blog_example::tag_create_logic;

    public entry fun create(
        name: String,
        tag_name_table: &mut tag::TagNameTable,
        ctx: &mut tx_context::TxContext,
    ) {
        let tag_created = tag_create_logic::verify(
            name,
            tag_name_table,
            ctx,
        );
        let tag = tag_create_logic::mutate(
            &tag_created,
            tag_name_table,
            ctx,
        );
        tag::set_tag_created_id(&mut tag_created, tag::id(&tag));
        tag::freeze_object(tag);
        tag::emit_tag_created(tag_created);
    }

}