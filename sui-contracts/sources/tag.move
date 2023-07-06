// <autogenerated>
//   This file was generated by dddappp code generator.
//   Any changes made to this file manually will be lost next time the file is regenerated.
// </autogenerated>

module sui_blog_example::tag {
    use std::option;
    use std::string::String;
    use sui::event;
    use sui::object::{Self, UID};
    use sui::table;
    use sui::transfer;
    use sui::tx_context::TxContext;
    friend sui_blog_example::tag_create_logic;
    friend sui_blog_example::tag_aggregate;

    const EID_ALREADY_EXISTS: u64 = 101;
    const EID_DATA_TOO_LONG: u64 = 102;
    const EINAPPROPRIATE_VERSION: u64 = 103;

    struct TagNameTable has key {
        id: UID,
        table: table::Table<String, object::ID>,
    }

    struct TagNameTableCreated has copy, drop {
        id: object::ID,
    }

    fun init(ctx: &mut TxContext) {
        let id_generator_table = TagNameTable {
            id: object::new(ctx),
            table: table::new(ctx),
        };
        let id_generator_table_id = object::uid_to_inner(&id_generator_table.id);
        transfer::share_object(id_generator_table);
        event::emit(TagNameTableCreated {
            id: id_generator_table_id,
        });
    }

    struct Tag has key {
        id: UID,
        name: String,
        version: u64,
    }

    public fun id(tag: &Tag): object::ID {
        object::uid_to_inner(&tag.id)
    }

    public fun name(tag: &Tag): String {
        tag.name
    }

    public fun version(tag: &Tag): u64 {
        tag.version
    }

    fun new_tag(
        name: String,
        ctx: &mut TxContext,
    ): Tag {
        assert!(std::string::length(&name) <= 50, EID_DATA_TOO_LONG);
        Tag {
            id: object::new(ctx),
            name,
            version: 0,
        }
    }

    struct TagCreated has copy, drop {
        id: option::Option<object::ID>,
        name: String,
    }

    public fun tag_created_id(tag_created: &TagCreated): option::Option<object::ID> {
        tag_created.id
    }

    public(friend) fun set_tag_created_id(tag_created: &mut TagCreated, id: object::ID) {
        tag_created.id = option::some(id);
    }

    public fun tag_created_name(tag_created: &TagCreated): String {
        tag_created.name
    }

    public(friend) fun new_tag_created(
        name: String,
    ): TagCreated {
        TagCreated {
            id: option::none(),
            name,
        }
    }


    public(friend) fun create_tag(
        name: String,
        tag_name_table: &mut TagNameTable,
        ctx: &mut TxContext,
    ): Tag {
        let tag = new_tag(
            name,
            ctx,
        );
        asset_name_not_exists_then_add(name, tag_name_table, object::uid_to_inner(&tag.id));
        tag
    }

    public(friend) fun asset_name_not_exists(
        name: String,
        tag_name_table: &TagNameTable,
    ) {
        assert!(!table::contains(&tag_name_table.table, name), EID_ALREADY_EXISTS);
    }

    fun asset_name_not_exists_then_add(
        name: String,
        tag_name_table: &mut TagNameTable,
        id: object::ID,
    ) {
        asset_name_not_exists(name, tag_name_table);
        table::add(&mut tag_name_table.table, name, id);
    }

    public(friend) fun transfer_object(tag: Tag, recipient: address) {
        assert!(tag.version == 0, EINAPPROPRIATE_VERSION);
        transfer::transfer(tag, recipient);
    }

    public(friend) fun update_version_and_transfer_object(tag: Tag, recipient: address) {
        update_object_version(&mut tag);
        transfer::transfer(tag, recipient);
    }

    public(friend) fun share_object(tag: Tag) {
        assert!(tag.version == 0, EINAPPROPRIATE_VERSION);
        transfer::share_object(tag);
    }

    public(friend) fun update_version_and_share_object(tag: Tag) {
        update_object_version(&mut tag);
        transfer::share_object(tag);
    }

    public(friend) fun freeze_object(tag: Tag) {
        assert!(tag.version == 0, EINAPPROPRIATE_VERSION);
        transfer::freeze_object(tag);
    }

    public(friend) fun update_version_and_freeze_object(tag: Tag) {
        update_object_version(&mut tag);
        transfer::freeze_object(tag);
    }

    fun update_object_version(tag: &mut Tag) {
        tag.version = tag.version + 1;
        //assert!(tag.version != 0, EINAPPROPRIATE_VERSION);
    }

    public(friend) fun drop_tag(tag: Tag) {
        let Tag {
            id,
            version: _version,
            name: _name,
        } = tag;
        object::delete(id);
    }

    public(friend) fun emit_tag_created(tag_created: TagCreated) {
        event::emit(tag_created);
    }

    #[test_only]
    /// Wrapper of module initializer for testing
    public fun test_init(ctx: &mut TxContext) {
        init(ctx)
    }

}