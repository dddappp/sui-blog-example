// <autogenerated>
//   This file was generated by dddappp code generator.
//   Any changes made to this file manually will be lost next time the file is regenerated.
// </autogenerated>

module sui_blog_example::article_created {

    use std::option;
    use std::string::String;
    use sui::object;
    use sui_blog_example::article::{Self, ArticleCreated};

    public fun id(article_created: &ArticleCreated): option::Option<object::ID> {
        article::article_created_id(article_created)
    }

    public fun title(article_created: &ArticleCreated): String {
        article::article_created_title(article_created)
    }

    public fun body(article_created: &ArticleCreated): String {
        article::article_created_body(article_created)
    }

}