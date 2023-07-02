// <autogenerated>
//   This file was generated by dddappp code generator.
//   Any changes made to this file manually will be lost next time the file is regenerated.
// </autogenerated>

module sui_blog_example::comment {
    use std::string::String;
    friend sui_blog_example::article_update_logic;
    friend sui_blog_example::article_update_comment_logic;
    friend sui_blog_example::article_remove_comment_logic;
    friend sui_blog_example::article_create_logic;
    friend sui_blog_example::article_delete_logic;
    friend sui_blog_example::article_add_comment_logic;
    friend sui_blog_example::article;

    const EID_DATA_TOO_LONG: u64 = 102;

    struct Comment has store {
        comment_seq_id: u64,
        commenter: String,
        body: String,
        owner: address,
    }

    public fun comment_seq_id(comment: &Comment): u64 {
        comment.comment_seq_id
    }

    public fun commenter(comment: &Comment): String {
        comment.commenter
    }

    public(friend) fun set_commenter(comment: &mut Comment, commenter: String) {
        assert!(std::string::length(&commenter) <= 100, EID_DATA_TOO_LONG);
        comment.commenter = commenter;
    }

    public fun body(comment: &Comment): String {
        comment.body
    }

    public(friend) fun set_body(comment: &mut Comment, body: String) {
        assert!(std::string::length(&body) <= 500, EID_DATA_TOO_LONG);
        comment.body = body;
    }

    public fun owner(comment: &Comment): address {
        comment.owner
    }

    public(friend) fun set_owner(comment: &mut Comment, owner: address) {
        comment.owner = owner;
    }

    public(friend) fun new_comment(
        comment_seq_id: u64,
        commenter: String,
        body: String,
        owner: address,
    ): Comment {
        assert!(std::string::length(&commenter) <= 100, EID_DATA_TOO_LONG);
        assert!(std::string::length(&body) <= 500, EID_DATA_TOO_LONG);
        Comment {
            comment_seq_id,
            commenter,
            body,
            owner,
        }
    }

    public(friend) fun drop_comment(comment: Comment) {
        let Comment {
            comment_seq_id: _,
            commenter: _,
            body: _,
            owner: _,
        } = comment;
    }


}
