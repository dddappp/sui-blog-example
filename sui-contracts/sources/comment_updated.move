// <autogenerated>
//   This file was generated by dddappp code generator.
//   Any changes made to this file manually will be lost next time the file is regenerated.
// </autogenerated>

module sui_blog_example::comment_updated {

    use std::string::String;
    use sui::object;
    use sui_blog_example::article::{Self, CommentUpdated};

    public fun id(comment_updated: &CommentUpdated): object::ID {
        article::comment_updated_id(comment_updated)
    }

    public fun comment_seq_id(comment_updated: &CommentUpdated): u64 {
        article::comment_updated_comment_seq_id(comment_updated)
    }

    public fun commenter(comment_updated: &CommentUpdated): String {
        article::comment_updated_commenter(comment_updated)
    }

    public fun body(comment_updated: &CommentUpdated): String {
        article::comment_updated_body(comment_updated)
    }

}
