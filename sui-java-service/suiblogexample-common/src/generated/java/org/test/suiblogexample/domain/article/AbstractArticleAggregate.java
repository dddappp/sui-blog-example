// <autogenerated>
//   This file was generated by dddappp code generator.
//   Any changes made to this file manually will be lost next time the file is regenerated.
// </autogenerated>

package org.test.suiblogexample.domain.article;

import java.util.*;
import java.math.BigInteger;
import java.util.Date;
import org.test.suiblogexample.domain.*;
import org.test.suiblogexample.specialization.*;

public abstract class AbstractArticleAggregate extends AbstractAggregate implements ArticleAggregate {
    private ArticleState.MutableArticleState state;

    private List<Event> changes = new ArrayList<Event>();

    public AbstractArticleAggregate(ArticleState state) {
        this.state = (ArticleState.MutableArticleState)state;
    }

    public ArticleState getState() {
        return this.state;
    }

    public List<Event> getChanges() {
        return this.changes;
    }

    public void throwOnInvalidStateTransition(Command c) {
        ArticleCommand.throwOnInvalidStateTransition(this.state, c);
    }

    protected void apply(Event e) {
        onApplying(e);
        state.mutate(e);
        changes.add(e);
    }


    ////////////////////////

    public static class SimpleArticleAggregate extends AbstractArticleAggregate {
        public SimpleArticleAggregate(ArticleState state) {
            super(state);
        }

        @Override
        public void create(String blog, String title, String body, String owner, Long offChainVersion, String commandId, String requesterId, ArticleCommands.Create c) {
            java.util.function.Supplier<ArticleEvent.ArticleCreated> eventFactory = () -> newArticleCreated(blog, title, body, owner, offChainVersion, commandId, requesterId);
            ArticleEvent.ArticleCreated e;
            try {
                e = verifyCreate(eventFactory, blog, title, body, owner, c);
            } catch (Exception ex) {
                throw new DomainError("VerificationFailed", ex);
            }

            apply(e);
        }

        @Override
        public void update(String title, String body, String owner, String[] tags, String[] tagsV2, Long offChainVersion, String commandId, String requesterId, ArticleCommands.Update c) {
            java.util.function.Supplier<ArticleEvent.ArticleUpdated> eventFactory = () -> newArticleUpdated(title, body, owner, tags, tagsV2, offChainVersion, commandId, requesterId);
            ArticleEvent.ArticleUpdated e;
            try {
                e = verifyUpdate(eventFactory, title, body, owner, tags, tagsV2, c);
            } catch (Exception ex) {
                throw new DomainError("VerificationFailed", ex);
            }

            apply(e);
        }

        @Override
        public void delete(String blog, Long offChainVersion, String commandId, String requesterId, ArticleCommands.Delete c) {
            java.util.function.Supplier<ArticleEvent.ArticleDeleted> eventFactory = () -> newArticleDeleted(blog, offChainVersion, commandId, requesterId);
            ArticleEvent.ArticleDeleted e;
            try {
                e = verifyDelete(eventFactory, blog, c);
            } catch (Exception ex) {
                throw new DomainError("VerificationFailed", ex);
            }

            apply(e);
        }

        @Override
        public void updateComment(BigInteger commentSeqId, String commenter, String body, String owner, Long offChainVersion, String commandId, String requesterId, ArticleCommands.UpdateComment c) {
            java.util.function.Supplier<ArticleEvent.CommentUpdated> eventFactory = () -> newCommentUpdated(commentSeqId, commenter, body, owner, offChainVersion, commandId, requesterId);
            ArticleEvent.CommentUpdated e;
            try {
                e = verifyUpdateComment(eventFactory, commentSeqId, commenter, body, owner, c);
            } catch (Exception ex) {
                throw new DomainError("VerificationFailed", ex);
            }

            apply(e);
        }

        @Override
        public void removeComment(BigInteger commentSeqId, Long offChainVersion, String commandId, String requesterId, ArticleCommands.RemoveComment c) {
            java.util.function.Supplier<ArticleEvent.CommentRemoved> eventFactory = () -> newCommentRemoved(commentSeqId, offChainVersion, commandId, requesterId);
            ArticleEvent.CommentRemoved e;
            try {
                e = verifyRemoveComment(eventFactory, commentSeqId, c);
            } catch (Exception ex) {
                throw new DomainError("VerificationFailed", ex);
            }

            apply(e);
        }

        @Override
        public void addComment(String commenter, String body, Long offChainVersion, String commandId, String requesterId, ArticleCommands.AddComment c) {
            java.util.function.Supplier<ArticleEvent.CommentAdded> eventFactory = () -> newCommentAdded(commenter, body, offChainVersion, commandId, requesterId);
            ArticleEvent.CommentAdded e;
            try {
                e = verifyAddComment(eventFactory, commenter, body, c);
            } catch (Exception ex) {
                throw new DomainError("VerificationFailed", ex);
            }

            apply(e);
        }

        @Override
        public void updateTags(String[] tags, Long offChainVersion, String commandId, String requesterId, ArticleCommands.UpdateTags c) {
            java.util.function.Supplier<ArticleEvent.ArticleTagsUpdated> eventFactory = () -> newArticleTagsUpdated(tags, offChainVersion, commandId, requesterId);
            ArticleEvent.ArticleTagsUpdated e;
            try {
                e = verifyUpdateTags(eventFactory, tags, c);
            } catch (Exception ex) {
                throw new DomainError("VerificationFailed", ex);
            }

            apply(e);
        }

        @Override
        public void updateTagsV2(String[] tags, Long offChainVersion, String commandId, String requesterId, ArticleCommands.UpdateTagsV2 c) {
            java.util.function.Supplier<ArticleEvent.ArticleTagsV2Updated> eventFactory = () -> newArticleTagsV2Updated(tags, offChainVersion, commandId, requesterId);
            ArticleEvent.ArticleTagsV2Updated e;
            try {
                e = verifyUpdateTagsV2(eventFactory, tags, c);
            } catch (Exception ex) {
                throw new DomainError("VerificationFailed", ex);
            }

            apply(e);
        }

        protected ArticleEvent.ArticleCreated verifyCreate(java.util.function.Supplier<ArticleEvent.ArticleCreated> eventFactory, String blog, String title, String body, String owner, ArticleCommands.Create c) {
            String Blog = blog;
            String Title = title;
            String Body = body;
            String Owner = owner;

            ArticleEvent.ArticleCreated e = (ArticleEvent.ArticleCreated) ReflectUtils.invokeStaticMethod(
                    "org.test.suiblogexample.domain.article.CreateLogic",
                    "verify",
                    new Class[]{java.util.function.Supplier.class, ArticleState.class, String.class, String.class, String.class, String.class, VerificationContext.class},
                    new Object[]{eventFactory, getState(), blog, title, body, owner, VerificationContext.forCommand(c)}
            );

//package org.test.suiblogexample.domain.article;
//
//public class CreateLogic {
//    public static ArticleEvent.ArticleCreated verify(java.util.function.Supplier<ArticleEvent.ArticleCreated> eventFactory, ArticleState articleState, String blog, String title, String body, String owner, VerificationContext verificationContext) {
//    }
//}

            return e;
        }
           

        protected ArticleEvent.ArticleUpdated verifyUpdate(java.util.function.Supplier<ArticleEvent.ArticleUpdated> eventFactory, String title, String body, String owner, String[] tags, String[] tagsV2, ArticleCommands.Update c) {
            String Title = title;
            String Body = body;
            String Owner = owner;
            String[] Tags = tags;
            String[] TagsV2 = tagsV2;

            ArticleEvent.ArticleUpdated e = (ArticleEvent.ArticleUpdated) ReflectUtils.invokeStaticMethod(
                    "org.test.suiblogexample.domain.article.UpdateLogic",
                    "verify",
                    new Class[]{java.util.function.Supplier.class, ArticleState.class, String.class, String.class, String.class, String[].class, String[].class, VerificationContext.class},
                    new Object[]{eventFactory, getState(), title, body, owner, tags, tagsV2, VerificationContext.forCommand(c)}
            );

//package org.test.suiblogexample.domain.article;
//
//public class UpdateLogic {
//    public static ArticleEvent.ArticleUpdated verify(java.util.function.Supplier<ArticleEvent.ArticleUpdated> eventFactory, ArticleState articleState, String title, String body, String owner, String[] tags, String[] tagsV2, VerificationContext verificationContext) {
//    }
//}

            return e;
        }
           

        protected ArticleEvent.ArticleDeleted verifyDelete(java.util.function.Supplier<ArticleEvent.ArticleDeleted> eventFactory, String blog, ArticleCommands.Delete c) {
            String Blog = blog;

            ArticleEvent.ArticleDeleted e = (ArticleEvent.ArticleDeleted) ReflectUtils.invokeStaticMethod(
                    "org.test.suiblogexample.domain.article.DeleteLogic",
                    "verify",
                    new Class[]{java.util.function.Supplier.class, ArticleState.class, String.class, VerificationContext.class},
                    new Object[]{eventFactory, getState(), blog, VerificationContext.forCommand(c)}
            );

//package org.test.suiblogexample.domain.article;
//
//public class DeleteLogic {
//    public static ArticleEvent.ArticleDeleted verify(java.util.function.Supplier<ArticleEvent.ArticleDeleted> eventFactory, ArticleState articleState, String blog, VerificationContext verificationContext) {
//    }
//}

            return e;
        }
           

        protected ArticleEvent.CommentUpdated verifyUpdateComment(java.util.function.Supplier<ArticleEvent.CommentUpdated> eventFactory, BigInteger commentSeqId, String commenter, String body, String owner, ArticleCommands.UpdateComment c) {
            BigInteger CommentSeqId = commentSeqId;
            String Commenter = commenter;
            String Body = body;
            String Owner = owner;

            ArticleEvent.CommentUpdated e = (ArticleEvent.CommentUpdated) ReflectUtils.invokeStaticMethod(
                    "org.test.suiblogexample.domain.article.UpdateCommentLogic",
                    "verify",
                    new Class[]{java.util.function.Supplier.class, ArticleState.class, BigInteger.class, String.class, String.class, String.class, VerificationContext.class},
                    new Object[]{eventFactory, getState(), commentSeqId, commenter, body, owner, VerificationContext.forCommand(c)}
            );

//package org.test.suiblogexample.domain.article;
//
//public class UpdateCommentLogic {
//    public static ArticleEvent.CommentUpdated verify(java.util.function.Supplier<ArticleEvent.CommentUpdated> eventFactory, ArticleState articleState, BigInteger commentSeqId, String commenter, String body, String owner, VerificationContext verificationContext) {
//    }
//}

            return e;
        }
           

        protected ArticleEvent.CommentRemoved verifyRemoveComment(java.util.function.Supplier<ArticleEvent.CommentRemoved> eventFactory, BigInteger commentSeqId, ArticleCommands.RemoveComment c) {
            BigInteger CommentSeqId = commentSeqId;

            ArticleEvent.CommentRemoved e = (ArticleEvent.CommentRemoved) ReflectUtils.invokeStaticMethod(
                    "org.test.suiblogexample.domain.article.RemoveCommentLogic",
                    "verify",
                    new Class[]{java.util.function.Supplier.class, ArticleState.class, BigInteger.class, VerificationContext.class},
                    new Object[]{eventFactory, getState(), commentSeqId, VerificationContext.forCommand(c)}
            );

//package org.test.suiblogexample.domain.article;
//
//public class RemoveCommentLogic {
//    public static ArticleEvent.CommentRemoved verify(java.util.function.Supplier<ArticleEvent.CommentRemoved> eventFactory, ArticleState articleState, BigInteger commentSeqId, VerificationContext verificationContext) {
//    }
//}

            return e;
        }
           

        protected ArticleEvent.CommentAdded verifyAddComment(java.util.function.Supplier<ArticleEvent.CommentAdded> eventFactory, String commenter, String body, ArticleCommands.AddComment c) {
            String Commenter = commenter;
            String Body = body;

            ArticleEvent.CommentAdded e = (ArticleEvent.CommentAdded) ReflectUtils.invokeStaticMethod(
                    "org.test.suiblogexample.domain.article.AddCommentLogic",
                    "verify",
                    new Class[]{java.util.function.Supplier.class, ArticleState.class, String.class, String.class, VerificationContext.class},
                    new Object[]{eventFactory, getState(), commenter, body, VerificationContext.forCommand(c)}
            );

//package org.test.suiblogexample.domain.article;
//
//public class AddCommentLogic {
//    public static ArticleEvent.CommentAdded verify(java.util.function.Supplier<ArticleEvent.CommentAdded> eventFactory, ArticleState articleState, String commenter, String body, VerificationContext verificationContext) {
//    }
//}

            return e;
        }
           

        protected ArticleEvent.ArticleTagsUpdated verifyUpdateTags(java.util.function.Supplier<ArticleEvent.ArticleTagsUpdated> eventFactory, String[] tags, ArticleCommands.UpdateTags c) {
            String[] Tags = tags;

            ArticleEvent.ArticleTagsUpdated e = (ArticleEvent.ArticleTagsUpdated) ReflectUtils.invokeStaticMethod(
                    "org.test.suiblogexample.domain.article.UpdateTagsLogic",
                    "verify",
                    new Class[]{java.util.function.Supplier.class, ArticleState.class, String[].class, VerificationContext.class},
                    new Object[]{eventFactory, getState(), tags, VerificationContext.forCommand(c)}
            );

//package org.test.suiblogexample.domain.article;
//
//public class UpdateTagsLogic {
//    public static ArticleEvent.ArticleTagsUpdated verify(java.util.function.Supplier<ArticleEvent.ArticleTagsUpdated> eventFactory, ArticleState articleState, String[] tags, VerificationContext verificationContext) {
//    }
//}

            return e;
        }
           

        protected ArticleEvent.ArticleTagsV2Updated verifyUpdateTagsV2(java.util.function.Supplier<ArticleEvent.ArticleTagsV2Updated> eventFactory, String[] tags, ArticleCommands.UpdateTagsV2 c) {
            String[] Tags = tags;

            ArticleEvent.ArticleTagsV2Updated e = (ArticleEvent.ArticleTagsV2Updated) ReflectUtils.invokeStaticMethod(
                    "org.test.suiblogexample.domain.article.UpdateTagsV2Logic",
                    "verify",
                    new Class[]{java.util.function.Supplier.class, ArticleState.class, String[].class, VerificationContext.class},
                    new Object[]{eventFactory, getState(), tags, VerificationContext.forCommand(c)}
            );

//package org.test.suiblogexample.domain.article;
//
//public class UpdateTagsV2Logic {
//    public static ArticleEvent.ArticleTagsV2Updated verify(java.util.function.Supplier<ArticleEvent.ArticleTagsV2Updated> eventFactory, ArticleState articleState, String[] tags, VerificationContext verificationContext) {
//    }
//}

            return e;
        }
           

        protected AbstractArticleEvent.ArticleCreated newArticleCreated(String blog, String title, String body, String owner, Long offChainVersion, String commandId, String requesterId) {
            ArticleEventId eventId = new ArticleEventId(getState().getId(), null);
            AbstractArticleEvent.ArticleCreated e = new AbstractArticleEvent.ArticleCreated();

            e.setBlogId(null);
            e.setTitle(title);
            e.setBody(body);
            e.setOwner(owner);
            e.setSuiTimestamp(null);
            e.setSuiTxDigest(null);
            e.setSuiEventSeq(null);
            e.setSuiPackageId(null);
            e.setSuiTransactionModule(null);
            e.setSuiSender(null);
            e.setSuiType(null);
            e.setStatus(null);

            e.setCommandId(commandId);
            e.setCreatedBy(requesterId);
            e.setCreatedAt((java.util.Date)ApplicationContext.current.getTimestampService().now(java.util.Date.class));

            e.setArticleEventId(eventId);
            return e;
        }

        protected AbstractArticleEvent.ArticleUpdated newArticleUpdated(String title, String body, String owner, String[] tags, String[] tagsV2, Long offChainVersion, String commandId, String requesterId) {
            ArticleEventId eventId = new ArticleEventId(getState().getId(), null);
            AbstractArticleEvent.ArticleUpdated e = new AbstractArticleEvent.ArticleUpdated();

            e.setTitle(title);
            e.setBody(body);
            e.setOwner(owner);
            e.setTags(tags);
            e.setTagsV2(tagsV2);
            e.setSuiTimestamp(null);
            e.setSuiTxDigest(null);
            e.setSuiEventSeq(null);
            e.setSuiPackageId(null);
            e.setSuiTransactionModule(null);
            e.setSuiSender(null);
            e.setSuiType(null);
            e.setStatus(null);

            e.setCommandId(commandId);
            e.setCreatedBy(requesterId);
            e.setCreatedAt((java.util.Date)ApplicationContext.current.getTimestampService().now(java.util.Date.class));

            e.setArticleEventId(eventId);
            return e;
        }

        protected AbstractArticleEvent.ArticleDeleted newArticleDeleted(String blog, Long offChainVersion, String commandId, String requesterId) {
            ArticleEventId eventId = new ArticleEventId(getState().getId(), null);
            AbstractArticleEvent.ArticleDeleted e = new AbstractArticleEvent.ArticleDeleted();

            e.setBlogId(null);
            e.setSuiTimestamp(null);
            e.setSuiTxDigest(null);
            e.setSuiEventSeq(null);
            e.setSuiPackageId(null);
            e.setSuiTransactionModule(null);
            e.setSuiSender(null);
            e.setSuiType(null);
            e.setStatus(null);

            e.setCommandId(commandId);
            e.setCreatedBy(requesterId);
            e.setCreatedAt((java.util.Date)ApplicationContext.current.getTimestampService().now(java.util.Date.class));

            e.setArticleEventId(eventId);
            return e;
        }

        protected AbstractArticleEvent.CommentUpdated newCommentUpdated(BigInteger commentSeqId, String commenter, String body, String owner, Long offChainVersion, String commandId, String requesterId) {
            ArticleEventId eventId = new ArticleEventId(getState().getId(), null);
            AbstractArticleEvent.CommentUpdated e = new AbstractArticleEvent.CommentUpdated();

            e.setCommentSeqId(commentSeqId);
            e.setCommenter(commenter);
            e.setBody(body);
            e.setOwner(owner);
            e.setSuiTimestamp(null);
            e.setSuiTxDigest(null);
            e.setSuiEventSeq(null);
            e.setSuiPackageId(null);
            e.setSuiTransactionModule(null);
            e.setSuiSender(null);
            e.setSuiType(null);
            e.setStatus(null);

            e.setCommandId(commandId);
            e.setCreatedBy(requesterId);
            e.setCreatedAt((java.util.Date)ApplicationContext.current.getTimestampService().now(java.util.Date.class));

            e.setArticleEventId(eventId);
            return e;
        }

        protected AbstractArticleEvent.CommentRemoved newCommentRemoved(BigInteger commentSeqId, Long offChainVersion, String commandId, String requesterId) {
            ArticleEventId eventId = new ArticleEventId(getState().getId(), null);
            AbstractArticleEvent.CommentRemoved e = new AbstractArticleEvent.CommentRemoved();

            e.setCommentSeqId(commentSeqId);
            e.setSuiTimestamp(null);
            e.setSuiTxDigest(null);
            e.setSuiEventSeq(null);
            e.setSuiPackageId(null);
            e.setSuiTransactionModule(null);
            e.setSuiSender(null);
            e.setSuiType(null);
            e.setStatus(null);

            e.setCommandId(commandId);
            e.setCreatedBy(requesterId);
            e.setCreatedAt((java.util.Date)ApplicationContext.current.getTimestampService().now(java.util.Date.class));

            e.setArticleEventId(eventId);
            return e;
        }

        protected AbstractArticleEvent.CommentAdded newCommentAdded(String commenter, String body, Long offChainVersion, String commandId, String requesterId) {
            ArticleEventId eventId = new ArticleEventId(getState().getId(), null);
            AbstractArticleEvent.CommentAdded e = new AbstractArticleEvent.CommentAdded();

            e.setCommentSeqId(null);
            e.setCommenter(commenter);
            e.setBody(body);
            e.setOwner(null);
            e.setSuiTimestamp(null);
            e.setSuiTxDigest(null);
            e.setSuiEventSeq(null);
            e.setSuiPackageId(null);
            e.setSuiTransactionModule(null);
            e.setSuiSender(null);
            e.setSuiType(null);
            e.setStatus(null);

            e.setCommandId(commandId);
            e.setCreatedBy(requesterId);
            e.setCreatedAt((java.util.Date)ApplicationContext.current.getTimestampService().now(java.util.Date.class));

            e.setArticleEventId(eventId);
            return e;
        }

        protected AbstractArticleEvent.ArticleTagsUpdated newArticleTagsUpdated(String[] tags, Long offChainVersion, String commandId, String requesterId) {
            ArticleEventId eventId = new ArticleEventId(getState().getId(), null);
            AbstractArticleEvent.ArticleTagsUpdated e = new AbstractArticleEvent.ArticleTagsUpdated();

            e.setTags(tags);
            e.setSuiTimestamp(null);
            e.setSuiTxDigest(null);
            e.setSuiEventSeq(null);
            e.setSuiPackageId(null);
            e.setSuiTransactionModule(null);
            e.setSuiSender(null);
            e.setSuiType(null);
            e.setStatus(null);

            e.setCommandId(commandId);
            e.setCreatedBy(requesterId);
            e.setCreatedAt((java.util.Date)ApplicationContext.current.getTimestampService().now(java.util.Date.class));

            e.setArticleEventId(eventId);
            return e;
        }

        protected AbstractArticleEvent.ArticleTagsV2Updated newArticleTagsV2Updated(String[] tags, Long offChainVersion, String commandId, String requesterId) {
            ArticleEventId eventId = new ArticleEventId(getState().getId(), null);
            AbstractArticleEvent.ArticleTagsV2Updated e = new AbstractArticleEvent.ArticleTagsV2Updated();

            e.setTags(tags);
            e.setSuiTimestamp(null);
            e.setSuiTxDigest(null);
            e.setSuiEventSeq(null);
            e.setSuiPackageId(null);
            e.setSuiTransactionModule(null);
            e.setSuiSender(null);
            e.setSuiType(null);
            e.setStatus(null);

            e.setCommandId(commandId);
            e.setCreatedBy(requesterId);
            e.setCreatedAt((java.util.Date)ApplicationContext.current.getTimestampService().now(java.util.Date.class));

            e.setArticleEventId(eventId);
            return e;
        }

    }

}

