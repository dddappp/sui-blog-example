module sui_blog_example::article_update_tags_logic {
    use std::string::String;
    use std::vector;

    use sui::tx_context::TxContext;

    use sui_blog_example::article;
    use sui_blog_example::article_tags_updated;
    use sui_blog_example::tag;
    use sui_blog_example::tag::Tag;

    friend sui_blog_example::article_aggregate;

    public(friend) fun verify(
        _tag_1: &Tag,
        _tag_2: &Tag,
        _tag_3: &Tag,
        article: &article::Article,
        ctx: &TxContext,
    ): article::ArticleTagsUpdated {
        let _ = ctx;
        let tag_names = vector::empty<String>();
        let tag_name = tag::name(_tag_1);
        if (!vector::contains(&tag_names, &tag_name)) {
            vector::push_back(&mut tag_names, tag_name)
        };
        let tag_name = tag::name(_tag_2);
        if (!vector::contains(&tag_names, &tag_name)) {
            vector::push_back(&mut tag_names, tag_name)
        };
        let tag_name = tag::name(_tag_3);
        if (!vector::contains(&tag_names, &tag_name)) {
            vector::push_back(&mut tag_names, tag_name)
        };
        article::new_article_tags_updated(
            article,
            tag_names,
        )
    }

    public(friend) fun mutate(
        article_tags_updated: &article::ArticleTagsUpdated,
        article: article::Article,
        _ctx: &TxContext, // modify the reference to mutable if needed
    ): article::Article {
        let tags = article_tags_updated::tags(article_tags_updated);
        let _id = article::id(&article);
        article::set_tags(&mut article, tags);
        article
    }
}
