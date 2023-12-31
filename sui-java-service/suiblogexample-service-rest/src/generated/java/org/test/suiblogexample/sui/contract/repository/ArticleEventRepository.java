// <autogenerated>
//   This file was generated by dddappp code generator.
//   Any changes made to this file manually will be lost next time the file is regenerated.
// </autogenerated>

package org.test.suiblogexample.sui.contract.repository;

import org.test.suiblogexample.domain.article.*;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.*;

public interface ArticleEventRepository extends JpaRepository<AbstractArticleEvent, ArticleEventId> {

    List<AbstractArticleEvent> findByStatusIsNull();

    AbstractArticleEvent.ArticleCreated findFirstArticleCreatedByOrderBySuiTimestampDesc();

    AbstractArticleEvent.ArticleUpdated findFirstArticleUpdatedByOrderBySuiTimestampDesc();

    AbstractArticleEvent.ArticleDeleted findFirstArticleDeletedByOrderBySuiTimestampDesc();

    AbstractArticleEvent.CommentUpdated findFirstCommentUpdatedByOrderBySuiTimestampDesc();

    AbstractArticleEvent.CommentRemoved findFirstCommentRemovedByOrderBySuiTimestampDesc();

    AbstractArticleEvent.CommentAdded findFirstCommentAddedByOrderBySuiTimestampDesc();

    AbstractArticleEvent.ArticleTagsUpdated findFirstArticleTagsUpdatedByOrderBySuiTimestampDesc();

    AbstractArticleEvent.ArticleTagsV2Updated findFirstArticleTagsV2UpdatedByOrderBySuiTimestampDesc();

}
