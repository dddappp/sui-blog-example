module sui_blog_example::article_update_tags_v2_logic {
    use std::vector;
    use sui::object::ID;
    use sui::tx_context::{TxContext};
    use sui_blog_example::article;
    use sui_blog_example::article_tags_v2_updated;
    use sui_blog_example::tag_v2::{Self, TagV2};

    friend sui_blog_example::article_aggregate;

    public(friend) fun verify(
        tags_0: &TagV2,
        tags_1: &TagV2,
        tags_2: &TagV2,
        article: &article::Article,
        ctx: &TxContext,
    ): article::ArticleTagsV2Updated {
        let _ = ctx;
        let tag_ids = vector::empty<ID>();
        let tag_id = tag_v2::id(tags_0);
        if (!vector::contains(&tag_ids, &tag_id)) {
            vector::push_back(&mut tag_ids, tag_id)
        };
        let tag_id = tag_v2::id(tags_1);
        if (!vector::contains(&tag_ids, &tag_id)) {
            vector::push_back(&mut tag_ids, tag_id)
        };
        let tag_id = tag_v2::id(tags_2);
        if (!vector::contains(&tag_ids, &tag_id)) {
            vector::push_back(&mut tag_ids, tag_id)
        };
        article::new_article_tags_v2_updated(
            article,
            tag_ids,
        )
    }

    public(friend) fun mutate(
        article_tags_v2_updated: &article::ArticleTagsV2Updated,
        article: article::Article,
        _ctx: &TxContext, // modify the reference to mutable if needed
    ): article::Article {
        let tags = article_tags_v2_updated::tags(article_tags_v2_updated);
        let _id = article::id(&article);
        article::set_tags_v2(&mut article, tags);
        article
    }

}
