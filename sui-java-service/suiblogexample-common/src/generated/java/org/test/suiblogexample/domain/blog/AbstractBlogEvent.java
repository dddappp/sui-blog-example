// <autogenerated>
//   This file was generated by dddappp code generator.
//   Any changes made to this file manually will be lost next time the file is regenerated.
// </autogenerated>

package org.test.suiblogexample.domain.blog;

import java.util.*;
import java.util.Date;
import java.math.BigInteger;
import org.test.suiblogexample.domain.*;
import org.test.suiblogexample.specialization.*;
import org.test.suiblogexample.domain.AbstractEvent;

public abstract class AbstractBlogEvent extends AbstractEvent implements BlogEvent.SqlBlogEvent, SuiEventEnvelope.MutableSuiEventEnvelope, SuiMoveEvent.MutableSuiMoveEvent, HasStatus.MutableHasStatus {
    private BlogEventId blogEventId = new BlogEventId();

    public BlogEventId getBlogEventId() {
        return this.blogEventId;
    }

    public void setBlogEventId(BlogEventId eventId) {
        this.blogEventId = eventId;
    }
    
    public String getId() {
        return getBlogEventId().getId();
    }

    public void setId(String id) {
        getBlogEventId().setId(id);
    }

    private boolean eventReadOnly;

    public boolean getEventReadOnly() { return this.eventReadOnly; }

    public void setEventReadOnly(boolean readOnly) { this.eventReadOnly = readOnly; }

    public BigInteger getVersion() {
        return getBlogEventId().getVersion();
    }
    
    public void setVersion(BigInteger version) {
        getBlogEventId().setVersion(version);
    }

    private Long suiTimestamp;

    public Long getSuiTimestamp() {
        return this.suiTimestamp;
    }
    
    public void setSuiTimestamp(Long suiTimestamp) {
        this.suiTimestamp = suiTimestamp;
    }

    private String suiTxDigest;

    public String getSuiTxDigest() {
        return this.suiTxDigest;
    }
    
    public void setSuiTxDigest(String suiTxDigest) {
        this.suiTxDigest = suiTxDigest;
    }

    private BigInteger suiEventSeq;

    public BigInteger getSuiEventSeq() {
        return this.suiEventSeq;
    }
    
    public void setSuiEventSeq(BigInteger suiEventSeq) {
        this.suiEventSeq = suiEventSeq;
    }

    private String suiPackageId;

    public String getSuiPackageId() {
        return this.suiPackageId;
    }
    
    public void setSuiPackageId(String suiPackageId) {
        this.suiPackageId = suiPackageId;
    }

    private String suiTransactionModule;

    public String getSuiTransactionModule() {
        return this.suiTransactionModule;
    }
    
    public void setSuiTransactionModule(String suiTransactionModule) {
        this.suiTransactionModule = suiTransactionModule;
    }

    private String suiSender;

    public String getSuiSender() {
        return this.suiSender;
    }
    
    public void setSuiSender(String suiSender) {
        this.suiSender = suiSender;
    }

    private String suiType;

    public String getSuiType() {
        return this.suiType;
    }
    
    public void setSuiType(String suiType) {
        this.suiType = suiType;
    }

    private String status;

    public String getStatus() {
        return this.status;
    }
    
    public void setStatus(String status) {
        this.status = status;
    }

    private String createdBy;

    public String getCreatedBy() {
        return this.createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    private Date createdAt;

    public Date getCreatedAt() {
        return this.createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }


    private String commandId;

    public String getCommandId() {
        return commandId;
    }

    public void setCommandId(String commandId) {
        this.commandId = commandId;
    }

    private String commandType;

    public String getCommandType() {
        return commandType;
    }

    public void setCommandType(String commandType) {
        this.commandType = commandType;
    }

    protected AbstractBlogEvent() {
    }

    protected AbstractBlogEvent(BlogEventId eventId) {
        this.blogEventId = eventId;
    }


    public abstract String getEventType();

    public static class BlogClobEvent extends  AbstractBlogEvent {

        protected Map<String, Object> getDynamicProperties() {
            return dynamicProperties;
        }

        protected void setDynamicProperties(Map<String, Object> dynamicProperties) {
            if (dynamicProperties == null) {
                throw new IllegalArgumentException("dynamicProperties is null.");
            }
            this.dynamicProperties = dynamicProperties;
        }

        private Map<String, Object> dynamicProperties = new HashMap<>();

        protected String getDynamicPropertiesLob() {
            return ApplicationContext.current.getClobConverter().toString(getDynamicProperties());
        }

        protected void setDynamicPropertiesLob(String text) {
            getDynamicProperties().clear();
            Map<String, Object> ps = ApplicationContext.current.getClobConverter().parseLobProperties(text);
            if (ps != null) {
                for (Map.Entry<String, Object> kv : ps.entrySet()) {
                    getDynamicProperties().put(kv.getKey(), kv.getValue());
                }
            }
        }

        @Override
        public String getEventType() {
            return "BlogClobEvent";
        }

    }

    public static class InitBlogEvent extends BlogClobEvent {

        @Override
        public String getEventType() {
            return "InitBlogEvent";
        }

    }

    public static class DonationReceived extends BlogClobEvent {

        @Override
        public String getEventType() {
            return "DonationReceived";
        }

        public BigInteger getAmount() {
            Object val = getDynamicProperties().get("amount");
            if (val instanceof BigInteger) {
                return (BigInteger) val;
            }
            return ApplicationContext.current.getTypeConverter().convertValue(val, BigInteger.class);
        }

        public void setAmount(BigInteger value) {
            getDynamicProperties().put("amount", value);
        }

    }

    public static class VaultWithdrawn extends BlogClobEvent {

        @Override
        public String getEventType() {
            return "VaultWithdrawn";
        }

        public BigInteger getAmount() {
            Object val = getDynamicProperties().get("amount");
            if (val instanceof BigInteger) {
                return (BigInteger) val;
            }
            return ApplicationContext.current.getTypeConverter().convertValue(val, BigInteger.class);
        }

        public void setAmount(BigInteger value) {
            getDynamicProperties().put("amount", value);
        }

    }

    public static class ArticleAddedToBlog extends BlogClobEvent {

        @Override
        public String getEventType() {
            return "ArticleAddedToBlog";
        }

        public String getArticleId() {
            Object val = getDynamicProperties().get("articleId");
            if (val instanceof String) {
                return (String) val;
            }
            return ApplicationContext.current.getTypeConverter().convertValue(val, String.class);
        }

        public void setArticleId(String value) {
            getDynamicProperties().put("articleId", value);
        }

    }

    public static class ArticleRemovedFromBlog extends BlogClobEvent {

        @Override
        public String getEventType() {
            return "ArticleRemovedFromBlog";
        }

        public String getArticleId() {
            Object val = getDynamicProperties().get("articleId");
            if (val instanceof String) {
                return (String) val;
            }
            return ApplicationContext.current.getTypeConverter().convertValue(val, String.class);
        }

        public void setArticleId(String value) {
            getDynamicProperties().put("articleId", value);
        }

    }


}

