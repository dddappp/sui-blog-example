// <autogenerated>
//   This file was generated by dddappp code generator.
//   Any changes made to this file manually will be lost next time the file is regenerated.
// </autogenerated>

module sui_blog_example::article_added_to_blog {

    use sui::object::{Self, ID};
    use sui_blog_example::blog::{Self, ArticleAddedToBlog};

    public fun id(article_added_to_blog: &ArticleAddedToBlog): object::ID {
        blog::article_added_to_blog_id(article_added_to_blog)
    }

    public fun article_id(article_added_to_blog: &ArticleAddedToBlog): ID {
        blog::article_added_to_blog_article_id(article_added_to_blog)
    }

}
