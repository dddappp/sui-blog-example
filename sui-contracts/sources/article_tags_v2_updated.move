// <autogenerated>
//   This file was generated by dddappp code generator.
//   Any changes made to this file manually will be lost next time the file is regenerated.
// </autogenerated>

module sui_blog_example::article_tags_v2_updated {

    use sui::object;
    use sui::object::ID;
    use sui_blog_example::article::{Self, ArticleTagsV2Updated};

    public fun id(article_tags_v2_updated: &ArticleTagsV2Updated): object::ID {
        article::article_tags_v2_updated_id(article_tags_v2_updated)
    }

    public fun tags(article_tags_v2_updated: &ArticleTagsV2Updated): vector<ID> {
        article::article_tags_v2_updated_tags(article_tags_v2_updated)
    }

}
