// <autogenerated>
//   This file was generated by dddappp code generator.
//   Any changes made to this file manually will be lost next time the file is regenerated.
// </autogenerated>

module sui_blog_example::tag_created {

    use std::option;
    use std::string::String;
    use sui::object;
    use sui_blog_example::tag::{Self, TagCreated};

    public fun id(tag_created: &TagCreated): option::Option<object::ID> {
        tag::tag_created_id(tag_created)
    }

    public fun name(tag_created: &TagCreated): String {
        tag::tag_created_name(tag_created)
    }

}
