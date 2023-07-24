// <autogenerated>
//   This file was generated by dddappp code generator.
//   Any changes made to this file manually will be lost next time the file is regenerated.
// </autogenerated>

package org.test.suiblogexample.sui.contract.taskservice;

import org.test.suiblogexample.sui.contract.service.ArticleEventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
public class PullArticleEventsTaskService {

    @Autowired
    private ArticleEventService articleEventService;

    @Scheduled(fixedDelayString = "${sui.contract.pull-article-events.article-created.fixed-delay:5000}")
    public void pullArticleCreatedEvents() {
        articleEventService.pullArticleCreatedEvents();
    }

    @Scheduled(fixedDelayString = "${sui.contract.pull-article-events.article-updated.fixed-delay:5000}")
    public void pullArticleUpdatedEvents() {
        articleEventService.pullArticleUpdatedEvents();
    }

    @Scheduled(fixedDelayString = "${sui.contract.pull-article-events.article-deleted.fixed-delay:5000}")
    public void pullArticleDeletedEvents() {
        articleEventService.pullArticleDeletedEvents();
    }

    @Scheduled(fixedDelayString = "${sui.contract.pull-article-events.comment-updated.fixed-delay:5000}")
    public void pullCommentUpdatedEvents() {
        articleEventService.pullCommentUpdatedEvents();
    }

    @Scheduled(fixedDelayString = "${sui.contract.pull-article-events.comment-removed.fixed-delay:5000}")
    public void pullCommentRemovedEvents() {
        articleEventService.pullCommentRemovedEvents();
    }

    @Scheduled(fixedDelayString = "${sui.contract.pull-article-events.comment-added.fixed-delay:5000}")
    public void pullCommentAddedEvents() {
        articleEventService.pullCommentAddedEvents();
    }

    @Scheduled(fixedDelayString = "${sui.contract.pull-article-events.article-tags-updated.fixed-delay:5000}")
    public void pullArticleTagsUpdatedEvents() {
        articleEventService.pullArticleTagsUpdatedEvents();
    }

    @Scheduled(fixedDelayString = "${sui.contract.pull-article-events.article-tags-v2-updated.fixed-delay:5000}")
    public void pullArticleTagsV2UpdatedEvents() {
        articleEventService.pullArticleTagsV2UpdatedEvents();
    }

}
