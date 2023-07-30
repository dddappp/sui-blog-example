// <autogenerated>
//   This file was generated by dddappp code generator.
//   Any changes made to this file manually will be lost next time the file is regenerated.
// </autogenerated>

package org.test.suiblogexample.sui.contract.repository;

import org.test.suiblogexample.domain.blog.*;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.*;

public interface BlogEventRepository extends JpaRepository<AbstractBlogEvent, BlogEventId> {

    List<AbstractBlogEvent> findByStatusIsNull();

    AbstractBlogEvent.InitBlogEvent findFirstInitBlogEventByOrderBySuiTimestampDesc();

    AbstractBlogEvent.DonationReceived findFirstDonationReceivedByOrderBySuiTimestampDesc();

    AbstractBlogEvent.VaultWithdrawn findFirstVaultWithdrawnByOrderBySuiTimestampDesc();

    AbstractBlogEvent.ArticleAddedToBlog findFirstArticleAddedToBlogByOrderBySuiTimestampDesc();

    AbstractBlogEvent.ArticleRemovedFromBlog findFirstArticleRemovedFromBlogByOrderBySuiTimestampDesc();

}