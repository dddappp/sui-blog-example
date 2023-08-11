// <autogenerated>
//   This file was generated by dddappp code generator.
//   Any changes made to this file manually will be lost next time the file is regenerated.
// </autogenerated>

package org.test.suiblogexample.sui.contract;

import java.math.*;

import com.github.wubuku.sui.bean.MoveEvent;
import com.github.wubuku.sui.bean.SuiMoveEventEnvelope;
import org.test.suiblogexample.domain.article.AbstractArticleEvent;
import org.test.suiblogexample.sui.contract.article.ArticleCreated;
import org.test.suiblogexample.sui.contract.article.ArticleUpdated;
import org.test.suiblogexample.sui.contract.article.ArticleDeleted;
import org.test.suiblogexample.sui.contract.article.CommentUpdated;
import org.test.suiblogexample.sui.contract.article.CommentRemoved;
import org.test.suiblogexample.sui.contract.article.CommentAdded;
import org.test.suiblogexample.sui.contract.article.ArticleTagsUpdated;
import org.test.suiblogexample.sui.contract.article.ArticleTagsV2Updated;
import org.test.suiblogexample.domain.tag.AbstractTagEvent;
import org.test.suiblogexample.sui.contract.tag.TagCreated;
import org.test.suiblogexample.domain.tagv2.AbstractTagV2Event;
import org.test.suiblogexample.sui.contract.tagv2.TagV2Created;
import org.test.suiblogexample.domain.blog.AbstractBlogEvent;
import org.test.suiblogexample.sui.contract.blog.InitBlogEvent;
import org.test.suiblogexample.sui.contract.blog.DonationReceived;
import org.test.suiblogexample.sui.contract.blog.VaultWithdrawn;
import org.test.suiblogexample.sui.contract.blog.ArticleAddedToBlog;
import org.test.suiblogexample.sui.contract.blog.ArticleRemovedFromBlog;
import org.test.suiblogexample.sui.contract.blog.BlogCreated;
import org.test.suiblogexample.sui.contract.blog.BlogUpdated;

/**
 * Utils that convert beans in the contract package to domain beans.
 */
public class DomainBeanUtils {
    private DomainBeanUtils() {
    }


    public static AbstractArticleEvent.ArticleCreated toArticleCreated(SuiMoveEventEnvelope<ArticleCreated> eventEnvelope) {
        ArticleCreated contractEvent = eventEnvelope.getParsedJson();

        AbstractArticleEvent.ArticleCreated articleCreated = new AbstractArticleEvent.ArticleCreated();
        articleCreated.setId(contractEvent.getId());
        articleCreated.setBlogId(contractEvent.getBlogId());
        articleCreated.setTitle(contractEvent.getTitle());
        articleCreated.setBody(contractEvent.getBody());
        articleCreated.setOwner(contractEvent.getOwner());
        articleCreated.setVersion(BigInteger.valueOf(-1));

        articleCreated.setSuiTimestamp(eventEnvelope.getTimestampMs());
        articleCreated.setSuiTxDigest(eventEnvelope.getId().getTxDigest());
        articleCreated.setSuiEventSeq(new BigInteger(eventEnvelope.getId().getEventSeq()));

        articleCreated.setSuiPackageId(eventEnvelope.getPackageId());
        articleCreated.setSuiTransactionModule(eventEnvelope.getTransactionModule());
        articleCreated.setSuiSender(eventEnvelope.getSender());

        return articleCreated;
    }

    public static AbstractArticleEvent.ArticleUpdated toArticleUpdated(SuiMoveEventEnvelope<ArticleUpdated> eventEnvelope) {
        ArticleUpdated contractEvent = eventEnvelope.getParsedJson();

        AbstractArticleEvent.ArticleUpdated articleUpdated = new AbstractArticleEvent.ArticleUpdated();
        articleUpdated.setId(contractEvent.getId());
        articleUpdated.setTitle(contractEvent.getTitle());
        articleUpdated.setBody(contractEvent.getBody());
        articleUpdated.setOwner(contractEvent.getOwner());
        articleUpdated.setTags(contractEvent.getTags());
        articleUpdated.setTagsV2(contractEvent.getTagsV2());
        articleUpdated.setVersion(contractEvent.getVersion());

        articleUpdated.setSuiTimestamp(eventEnvelope.getTimestampMs());
        articleUpdated.setSuiTxDigest(eventEnvelope.getId().getTxDigest());
        articleUpdated.setSuiEventSeq(new BigInteger(eventEnvelope.getId().getEventSeq()));

        articleUpdated.setSuiPackageId(eventEnvelope.getPackageId());
        articleUpdated.setSuiTransactionModule(eventEnvelope.getTransactionModule());
        articleUpdated.setSuiSender(eventEnvelope.getSender());

        return articleUpdated;
    }

    public static AbstractArticleEvent.ArticleDeleted toArticleDeleted(SuiMoveEventEnvelope<ArticleDeleted> eventEnvelope) {
        ArticleDeleted contractEvent = eventEnvelope.getParsedJson();

        AbstractArticleEvent.ArticleDeleted articleDeleted = new AbstractArticleEvent.ArticleDeleted();
        articleDeleted.setId(contractEvent.getId());
        articleDeleted.setBlogId(contractEvent.getBlogId());
        articleDeleted.setVersion(contractEvent.getVersion());

        articleDeleted.setSuiTimestamp(eventEnvelope.getTimestampMs());
        articleDeleted.setSuiTxDigest(eventEnvelope.getId().getTxDigest());
        articleDeleted.setSuiEventSeq(new BigInteger(eventEnvelope.getId().getEventSeq()));

        articleDeleted.setSuiPackageId(eventEnvelope.getPackageId());
        articleDeleted.setSuiTransactionModule(eventEnvelope.getTransactionModule());
        articleDeleted.setSuiSender(eventEnvelope.getSender());

        return articleDeleted;
    }

    public static AbstractArticleEvent.CommentUpdated toCommentUpdated(SuiMoveEventEnvelope<CommentUpdated> eventEnvelope) {
        CommentUpdated contractEvent = eventEnvelope.getParsedJson();

        AbstractArticleEvent.CommentUpdated commentUpdated = new AbstractArticleEvent.CommentUpdated();
        commentUpdated.setId(contractEvent.getId());
        commentUpdated.setCommentSeqId(contractEvent.getCommentSeqId());
        commentUpdated.setCommenter(contractEvent.getCommenter());
        commentUpdated.setBody(contractEvent.getBody());
        commentUpdated.setOwner(contractEvent.getOwner());
        commentUpdated.setVersion(contractEvent.getVersion());

        commentUpdated.setSuiTimestamp(eventEnvelope.getTimestampMs());
        commentUpdated.setSuiTxDigest(eventEnvelope.getId().getTxDigest());
        commentUpdated.setSuiEventSeq(new BigInteger(eventEnvelope.getId().getEventSeq()));

        commentUpdated.setSuiPackageId(eventEnvelope.getPackageId());
        commentUpdated.setSuiTransactionModule(eventEnvelope.getTransactionModule());
        commentUpdated.setSuiSender(eventEnvelope.getSender());

        return commentUpdated;
    }

    public static AbstractArticleEvent.CommentRemoved toCommentRemoved(SuiMoveEventEnvelope<CommentRemoved> eventEnvelope) {
        CommentRemoved contractEvent = eventEnvelope.getParsedJson();

        AbstractArticleEvent.CommentRemoved commentRemoved = new AbstractArticleEvent.CommentRemoved();
        commentRemoved.setId(contractEvent.getId());
        commentRemoved.setCommentSeqId(contractEvent.getCommentSeqId());
        commentRemoved.setVersion(contractEvent.getVersion());

        commentRemoved.setSuiTimestamp(eventEnvelope.getTimestampMs());
        commentRemoved.setSuiTxDigest(eventEnvelope.getId().getTxDigest());
        commentRemoved.setSuiEventSeq(new BigInteger(eventEnvelope.getId().getEventSeq()));

        commentRemoved.setSuiPackageId(eventEnvelope.getPackageId());
        commentRemoved.setSuiTransactionModule(eventEnvelope.getTransactionModule());
        commentRemoved.setSuiSender(eventEnvelope.getSender());

        return commentRemoved;
    }

    public static AbstractArticleEvent.CommentAdded toCommentAdded(SuiMoveEventEnvelope<CommentAdded> eventEnvelope) {
        CommentAdded contractEvent = eventEnvelope.getParsedJson();

        AbstractArticleEvent.CommentAdded commentAdded = new AbstractArticleEvent.CommentAdded();
        commentAdded.setId(contractEvent.getId());
        commentAdded.setCommentSeqId(contractEvent.getCommentSeqId());
        commentAdded.setCommenter(contractEvent.getCommenter());
        commentAdded.setBody(contractEvent.getBody());
        commentAdded.setOwner(contractEvent.getOwner());
        commentAdded.setVersion(contractEvent.getVersion());

        commentAdded.setSuiTimestamp(eventEnvelope.getTimestampMs());
        commentAdded.setSuiTxDigest(eventEnvelope.getId().getTxDigest());
        commentAdded.setSuiEventSeq(new BigInteger(eventEnvelope.getId().getEventSeq()));

        commentAdded.setSuiPackageId(eventEnvelope.getPackageId());
        commentAdded.setSuiTransactionModule(eventEnvelope.getTransactionModule());
        commentAdded.setSuiSender(eventEnvelope.getSender());

        return commentAdded;
    }

    public static AbstractArticleEvent.ArticleTagsUpdated toArticleTagsUpdated(SuiMoveEventEnvelope<ArticleTagsUpdated> eventEnvelope) {
        ArticleTagsUpdated contractEvent = eventEnvelope.getParsedJson();

        AbstractArticleEvent.ArticleTagsUpdated articleTagsUpdated = new AbstractArticleEvent.ArticleTagsUpdated();
        articleTagsUpdated.setId(contractEvent.getId());
        articleTagsUpdated.setTags(contractEvent.getTags());
        articleTagsUpdated.setVersion(contractEvent.getVersion());

        articleTagsUpdated.setSuiTimestamp(eventEnvelope.getTimestampMs());
        articleTagsUpdated.setSuiTxDigest(eventEnvelope.getId().getTxDigest());
        articleTagsUpdated.setSuiEventSeq(new BigInteger(eventEnvelope.getId().getEventSeq()));

        articleTagsUpdated.setSuiPackageId(eventEnvelope.getPackageId());
        articleTagsUpdated.setSuiTransactionModule(eventEnvelope.getTransactionModule());
        articleTagsUpdated.setSuiSender(eventEnvelope.getSender());

        return articleTagsUpdated;
    }

    public static AbstractArticleEvent.ArticleTagsV2Updated toArticleTagsV2Updated(SuiMoveEventEnvelope<ArticleTagsV2Updated> eventEnvelope) {
        ArticleTagsV2Updated contractEvent = eventEnvelope.getParsedJson();

        AbstractArticleEvent.ArticleTagsV2Updated articleTagsV2Updated = new AbstractArticleEvent.ArticleTagsV2Updated();
        articleTagsV2Updated.setId(contractEvent.getId());
        articleTagsV2Updated.setTags(contractEvent.getTags());
        articleTagsV2Updated.setVersion(contractEvent.getVersion());

        articleTagsV2Updated.setSuiTimestamp(eventEnvelope.getTimestampMs());
        articleTagsV2Updated.setSuiTxDigest(eventEnvelope.getId().getTxDigest());
        articleTagsV2Updated.setSuiEventSeq(new BigInteger(eventEnvelope.getId().getEventSeq()));

        articleTagsV2Updated.setSuiPackageId(eventEnvelope.getPackageId());
        articleTagsV2Updated.setSuiTransactionModule(eventEnvelope.getTransactionModule());
        articleTagsV2Updated.setSuiSender(eventEnvelope.getSender());

        return articleTagsV2Updated;
    }

    public static AbstractTagEvent.TagCreated toTagCreated(SuiMoveEventEnvelope<TagCreated> eventEnvelope) {
        TagCreated contractEvent = eventEnvelope.getParsedJson();

        AbstractTagEvent.TagCreated tagCreated = new AbstractTagEvent.TagCreated();
        tagCreated.setName(contractEvent.getName());
        tagCreated.setId_(contractEvent.getId());
        tagCreated.setVersion(BigInteger.valueOf(-1));

        tagCreated.setSuiTimestamp(eventEnvelope.getTimestampMs());
        tagCreated.setSuiTxDigest(eventEnvelope.getId().getTxDigest());
        tagCreated.setSuiEventSeq(new BigInteger(eventEnvelope.getId().getEventSeq()));

        tagCreated.setSuiPackageId(eventEnvelope.getPackageId());
        tagCreated.setSuiTransactionModule(eventEnvelope.getTransactionModule());
        tagCreated.setSuiSender(eventEnvelope.getSender());

        return tagCreated;
    }

    public static AbstractTagV2Event.TagV2Created toTagV2Created(SuiMoveEventEnvelope<TagV2Created> eventEnvelope) {
        TagV2Created contractEvent = eventEnvelope.getParsedJson();

        AbstractTagV2Event.TagV2Created tagV2Created = new AbstractTagV2Event.TagV2Created();
        tagV2Created.setId(contractEvent.getId());
        tagV2Created.setName(contractEvent.getName());
        tagV2Created.setVersion(BigInteger.valueOf(-1));

        tagV2Created.setSuiTimestamp(eventEnvelope.getTimestampMs());
        tagV2Created.setSuiTxDigest(eventEnvelope.getId().getTxDigest());
        tagV2Created.setSuiEventSeq(new BigInteger(eventEnvelope.getId().getEventSeq()));

        tagV2Created.setSuiPackageId(eventEnvelope.getPackageId());
        tagV2Created.setSuiTransactionModule(eventEnvelope.getTransactionModule());
        tagV2Created.setSuiSender(eventEnvelope.getSender());

        return tagV2Created;
    }

    public static AbstractBlogEvent.InitBlogEvent toInitBlogEvent(SuiMoveEventEnvelope<InitBlogEvent> eventEnvelope) {
        InitBlogEvent contractEvent = eventEnvelope.getParsedJson();

        AbstractBlogEvent.InitBlogEvent initBlogEvent = new AbstractBlogEvent.InitBlogEvent();
        initBlogEvent.setId(contractEvent.getId());
        initBlogEvent.setVersion(BigInteger.valueOf(-1));

        initBlogEvent.setSuiTimestamp(eventEnvelope.getTimestampMs());
        initBlogEvent.setSuiTxDigest(eventEnvelope.getId().getTxDigest());
        initBlogEvent.setSuiEventSeq(new BigInteger(eventEnvelope.getId().getEventSeq()));

        initBlogEvent.setSuiPackageId(eventEnvelope.getPackageId());
        initBlogEvent.setSuiTransactionModule(eventEnvelope.getTransactionModule());
        initBlogEvent.setSuiSender(eventEnvelope.getSender());

        return initBlogEvent;
    }

    public static AbstractBlogEvent.DonationReceived toDonationReceived(SuiMoveEventEnvelope<DonationReceived> eventEnvelope) {
        DonationReceived contractEvent = eventEnvelope.getParsedJson();

        AbstractBlogEvent.DonationReceived donationReceived = new AbstractBlogEvent.DonationReceived();
        donationReceived.setId(contractEvent.getId());
        donationReceived.setAmount(contractEvent.getAmount());
        donationReceived.setVersion(contractEvent.getVersion());

        donationReceived.setSuiTimestamp(eventEnvelope.getTimestampMs());
        donationReceived.setSuiTxDigest(eventEnvelope.getId().getTxDigest());
        donationReceived.setSuiEventSeq(new BigInteger(eventEnvelope.getId().getEventSeq()));

        donationReceived.setSuiPackageId(eventEnvelope.getPackageId());
        donationReceived.setSuiTransactionModule(eventEnvelope.getTransactionModule());
        donationReceived.setSuiSender(eventEnvelope.getSender());

        return donationReceived;
    }

    public static AbstractBlogEvent.VaultWithdrawn toVaultWithdrawn(SuiMoveEventEnvelope<VaultWithdrawn> eventEnvelope) {
        VaultWithdrawn contractEvent = eventEnvelope.getParsedJson();

        AbstractBlogEvent.VaultWithdrawn vaultWithdrawn = new AbstractBlogEvent.VaultWithdrawn();
        vaultWithdrawn.setId(contractEvent.getId());
        vaultWithdrawn.setAmount(contractEvent.getAmount());
        vaultWithdrawn.setVersion(contractEvent.getVersion());

        vaultWithdrawn.setSuiTimestamp(eventEnvelope.getTimestampMs());
        vaultWithdrawn.setSuiTxDigest(eventEnvelope.getId().getTxDigest());
        vaultWithdrawn.setSuiEventSeq(new BigInteger(eventEnvelope.getId().getEventSeq()));

        vaultWithdrawn.setSuiPackageId(eventEnvelope.getPackageId());
        vaultWithdrawn.setSuiTransactionModule(eventEnvelope.getTransactionModule());
        vaultWithdrawn.setSuiSender(eventEnvelope.getSender());

        return vaultWithdrawn;
    }

    public static AbstractBlogEvent.ArticleAddedToBlog toArticleAddedToBlog(SuiMoveEventEnvelope<ArticleAddedToBlog> eventEnvelope) {
        ArticleAddedToBlog contractEvent = eventEnvelope.getParsedJson();

        AbstractBlogEvent.ArticleAddedToBlog articleAddedToBlog = new AbstractBlogEvent.ArticleAddedToBlog();
        articleAddedToBlog.setId(contractEvent.getId());
        articleAddedToBlog.setArticleId(contractEvent.getArticleId());
        articleAddedToBlog.setVersion(contractEvent.getVersion());

        articleAddedToBlog.setSuiTimestamp(eventEnvelope.getTimestampMs());
        articleAddedToBlog.setSuiTxDigest(eventEnvelope.getId().getTxDigest());
        articleAddedToBlog.setSuiEventSeq(new BigInteger(eventEnvelope.getId().getEventSeq()));

        articleAddedToBlog.setSuiPackageId(eventEnvelope.getPackageId());
        articleAddedToBlog.setSuiTransactionModule(eventEnvelope.getTransactionModule());
        articleAddedToBlog.setSuiSender(eventEnvelope.getSender());

        return articleAddedToBlog;
    }

    public static AbstractBlogEvent.ArticleRemovedFromBlog toArticleRemovedFromBlog(SuiMoveEventEnvelope<ArticleRemovedFromBlog> eventEnvelope) {
        ArticleRemovedFromBlog contractEvent = eventEnvelope.getParsedJson();

        AbstractBlogEvent.ArticleRemovedFromBlog articleRemovedFromBlog = new AbstractBlogEvent.ArticleRemovedFromBlog();
        articleRemovedFromBlog.setId(contractEvent.getId());
        articleRemovedFromBlog.setArticleId(contractEvent.getArticleId());
        articleRemovedFromBlog.setVersion(contractEvent.getVersion());

        articleRemovedFromBlog.setSuiTimestamp(eventEnvelope.getTimestampMs());
        articleRemovedFromBlog.setSuiTxDigest(eventEnvelope.getId().getTxDigest());
        articleRemovedFromBlog.setSuiEventSeq(new BigInteger(eventEnvelope.getId().getEventSeq()));

        articleRemovedFromBlog.setSuiPackageId(eventEnvelope.getPackageId());
        articleRemovedFromBlog.setSuiTransactionModule(eventEnvelope.getTransactionModule());
        articleRemovedFromBlog.setSuiSender(eventEnvelope.getSender());

        return articleRemovedFromBlog;
    }

    public static AbstractBlogEvent.BlogCreated toBlogCreated(SuiMoveEventEnvelope<BlogCreated> eventEnvelope) {
        BlogCreated contractEvent = eventEnvelope.getParsedJson();

        AbstractBlogEvent.BlogCreated blogCreated = new AbstractBlogEvent.BlogCreated();
        blogCreated.setId(contractEvent.getId());
        blogCreated.setArticles(contractEvent.getArticles());
        blogCreated.setVersion(BigInteger.valueOf(-1));

        blogCreated.setSuiTimestamp(eventEnvelope.getTimestampMs());
        blogCreated.setSuiTxDigest(eventEnvelope.getId().getTxDigest());
        blogCreated.setSuiEventSeq(new BigInteger(eventEnvelope.getId().getEventSeq()));

        blogCreated.setSuiPackageId(eventEnvelope.getPackageId());
        blogCreated.setSuiTransactionModule(eventEnvelope.getTransactionModule());
        blogCreated.setSuiSender(eventEnvelope.getSender());

        return blogCreated;
    }

    public static AbstractBlogEvent.BlogUpdated toBlogUpdated(SuiMoveEventEnvelope<BlogUpdated> eventEnvelope) {
        BlogUpdated contractEvent = eventEnvelope.getParsedJson();

        AbstractBlogEvent.BlogUpdated blogUpdated = new AbstractBlogEvent.BlogUpdated();
        blogUpdated.setId(contractEvent.getId());
        blogUpdated.setName(contractEvent.getName());
        blogUpdated.setArticles(contractEvent.getArticles());
        blogUpdated.setVersion(contractEvent.getVersion());

        blogUpdated.setSuiTimestamp(eventEnvelope.getTimestampMs());
        blogUpdated.setSuiTxDigest(eventEnvelope.getId().getTxDigest());
        blogUpdated.setSuiEventSeq(new BigInteger(eventEnvelope.getId().getEventSeq()));

        blogUpdated.setSuiPackageId(eventEnvelope.getPackageId());
        blogUpdated.setSuiTransactionModule(eventEnvelope.getTransactionModule());
        blogUpdated.setSuiSender(eventEnvelope.getSender());

        return blogUpdated;
    }

}
