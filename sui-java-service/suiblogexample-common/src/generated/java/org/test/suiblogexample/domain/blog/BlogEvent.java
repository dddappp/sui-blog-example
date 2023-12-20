// <autogenerated>
//   This file was generated by dddappp code generator.
//   Any changes made to this file manually will be lost next time the file is regenerated.
// </autogenerated>

package org.test.suiblogexample.domain.blog;

import java.util.*;
import java.util.Date;
import java.math.BigInteger;
import org.test.suiblogexample.domain.*;
import org.test.suiblogexample.specialization.Event;

public interface BlogEvent extends Event, SuiEventEnvelope, SuiMoveEvent, HasStatus {

    interface SqlBlogEvent extends BlogEvent {
        BlogEventId getBlogEventId();

        boolean getEventReadOnly();

        void setEventReadOnly(boolean readOnly);
    }

    interface InitBlogEvent extends BlogEvent {
    }

    interface DonationReceived extends BlogEvent {
        BigInteger getAmount();

        void setAmount(BigInteger value);

    }

    interface VaultWithdrawn extends BlogEvent {
        BigInteger getAmount();

        void setAmount(BigInteger value);

    }

    interface ArticleAddedToBlog extends BlogEvent {
        String getArticleId();

        void setArticleId(String value);

    }

    interface ArticleRemovedFromBlog extends BlogEvent {
        String getArticleId();

        void setArticleId(String value);

    }

    interface BlogCreated extends BlogEvent {
        String[] getArticles();

        void setArticles(String[] value);

    }

    interface BlogUpdated extends BlogEvent {
        String getName();

        void setName(String value);

        String[] getArticles();

        void setArticles(String[] value);

    }

    String getId();

    //void setId(String id);

    BigInteger getVersion();
    
    //void setVersion(BigInteger version);

    String getCreatedBy();

    void setCreatedBy(String createdBy);

    Date getCreatedAt();

    void setCreatedAt(Date createdAt);

    String getCommandId();

    void setCommandId(String commandId);


}

