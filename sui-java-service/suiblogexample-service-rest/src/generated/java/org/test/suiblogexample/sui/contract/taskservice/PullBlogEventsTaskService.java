// <autogenerated>
//   This file was generated by dddappp code generator.
//   Any changes made to this file manually will be lost next time the file is regenerated.
// </autogenerated>

package org.test.suiblogexample.sui.contract.taskservice;

import org.test.suiblogexample.sui.contract.service.BlogEventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
public class PullBlogEventsTaskService {

    @Autowired
    private BlogEventService blogEventService;

    @Scheduled(fixedDelayString = "${sui.contract.pull-blog-events.init-blog-event.fixed-delay:5000}")
    public void pullInitBlogEvents() {
        blogEventService.pullInitBlogEvents();
    }

    @Scheduled(fixedDelayString = "${sui.contract.pull-blog-events.donation-received.fixed-delay:5000}")
    public void pullDonationReceivedEvents() {
        blogEventService.pullDonationReceivedEvents();
    }

    @Scheduled(fixedDelayString = "${sui.contract.pull-blog-events.vault-withdrawn.fixed-delay:5000}")
    public void pullVaultWithdrawnEvents() {
        blogEventService.pullVaultWithdrawnEvents();
    }

    @Scheduled(fixedDelayString = "${sui.contract.pull-blog-events.article-added-to-blog.fixed-delay:5000}")
    public void pullArticleAddedToBlogEvents() {
        blogEventService.pullArticleAddedToBlogEvents();
    }

    @Scheduled(fixedDelayString = "${sui.contract.pull-blog-events.article-removed-from-blog.fixed-delay:5000}")
    public void pullArticleRemovedFromBlogEvents() {
        blogEventService.pullArticleRemovedFromBlogEvents();
    }

    @Scheduled(fixedDelayString = "${sui.contract.pull-blog-events.blog-created.fixed-delay:5000}")
    public void pullBlogCreatedEvents() {
        blogEventService.pullBlogCreatedEvents();
    }

    @Scheduled(fixedDelayString = "${sui.contract.pull-blog-events.blog-updated.fixed-delay:5000}")
    public void pullBlogUpdatedEvents() {
        blogEventService.pullBlogUpdatedEvents();
    }

}
