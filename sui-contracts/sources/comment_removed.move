// <autogenerated>
//   This file was generated by dddappp code generator.
//   Any changes made to this file manually will be lost next time the file is regenerated.
// </autogenerated>

module sui_blog_example::comment_removed {

    use sui::object;
    use sui_blog_example::article::{Self, CommentRemoved};

    public fun id(comment_removed: &CommentRemoved): object::ID {
        article::comment_removed_id(comment_removed)
    }

    public fun comment_seq_id(comment_removed: &CommentRemoved): u64 {
        article::comment_removed_comment_seq_id(comment_removed)
    }

}