// <autogenerated>
//   This file was generated by dddappp code generator.
//   Any changes made to this file manually will be lost next time the file is regenerated.
// </autogenerated>

module sui_blog_example::tag_v2 {
    use std::option;
    use std::string::String;
    use sui::event;
    use sui::object::{Self, UID};
    use sui::transfer;
    use sui::tx_context::TxContext;
    friend sui_blog_example::tag_v2_create_logic;
    friend sui_blog_example::tag_v2_aggregate;

    const EID_DATA_TOO_LONG: u64 = 102;
    const EINAPPROPRIATE_VERSION: u64 = 103;

    struct TagV2 has key {
        id: UID,
        version: u64,
        name: String,
    }

    public fun id(tag_v2: &TagV2): object::ID {
        object::uid_to_inner(&tag_v2.id)
    }

    public fun version(tag_v2: &TagV2): u64 {
        tag_v2.version
    }

    public fun name(tag_v2: &TagV2): String {
        tag_v2.name
    }

    public(friend) fun set_name(tag_v2: &mut TagV2, name: String) {
        assert!(std::string::length(&name) <= 100, EID_DATA_TOO_LONG);
        tag_v2.name = name;
    }

    public(friend) fun new_tag_v2(
        name: String,
        ctx: &mut TxContext,
    ): TagV2 {
        assert!(std::string::length(&name) <= 100, EID_DATA_TOO_LONG);
        TagV2 {
            id: object::new(ctx),
            version: 0,
            name,
        }
    }

    struct TagV2Created has copy, drop {
        id: option::Option<object::ID>,
        name: String,
    }

    public fun tag_v2_created_id(tag_v2_created: &TagV2Created): option::Option<object::ID> {
        tag_v2_created.id
    }

    public(friend) fun set_tag_v2_created_id(tag_v2_created: &mut TagV2Created, id: object::ID) {
        tag_v2_created.id = option::some(id);
    }

    public fun tag_v2_created_name(tag_v2_created: &TagV2Created): String {
        tag_v2_created.name
    }

    public(friend) fun new_tag_v2_created(
        name: String,
    ): TagV2Created {
        TagV2Created {
            id: option::none(),
            name,
        }
    }


    public(friend) fun transfer_object(tag_v2: TagV2, recipient: address) {
        assert!(tag_v2.version == 0, EINAPPROPRIATE_VERSION);
        transfer::transfer(tag_v2, recipient);
    }

    public(friend) fun update_version_and_transfer_object(tag_v2: TagV2, recipient: address) {
        update_object_version(&mut tag_v2);
        transfer::transfer(tag_v2, recipient);
    }

    public(friend) fun share_object(tag_v2: TagV2) {
        assert!(tag_v2.version == 0, EINAPPROPRIATE_VERSION);
        transfer::share_object(tag_v2);
    }

    public(friend) fun update_version_and_share_object(tag_v2: TagV2) {
        update_object_version(&mut tag_v2);
        transfer::share_object(tag_v2);
    }

    public(friend) fun freeze_object(tag_v2: TagV2) {
        assert!(tag_v2.version == 0, EINAPPROPRIATE_VERSION);
        transfer::freeze_object(tag_v2);
    }

    public(friend) fun update_version_and_freeze_object(tag_v2: TagV2) {
        update_object_version(&mut tag_v2);
        transfer::freeze_object(tag_v2);
    }

    fun update_object_version(tag_v2: &mut TagV2) {
        tag_v2.version = tag_v2.version + 1;
        //assert!(tag_v2.version != 0, EINAPPROPRIATE_VERSION);
    }

    public(friend) fun drop_tag_v2(tag_v2: TagV2) {
        let TagV2 {
            id,
            version: _version,
            name: _name,
        } = tag_v2;
        object::delete(id);
    }

    public(friend) fun emit_tag_v2_created(tag_v2_created: TagV2Created) {
        event::emit(tag_v2_created);
    }

}
