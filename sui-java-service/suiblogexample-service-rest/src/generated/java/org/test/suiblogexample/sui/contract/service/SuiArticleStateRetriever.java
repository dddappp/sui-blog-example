// <autogenerated>
//   This file was generated by dddappp code generator.
//   Any changes made to this file manually will be lost next time the file is regenerated.
// </autogenerated>

package org.test.suiblogexample.sui.contract.service;

import com.github.wubuku.sui.bean.*;
import com.github.wubuku.sui.utils.*;
import org.test.suiblogexample.domain.article.*;
import org.test.suiblogexample.domain.*;
import org.test.suiblogexample.sui.contract.DomainBeanUtils;
import org.test.suiblogexample.sui.contract.Article;
import org.test.suiblogexample.sui.contract.Comment;
import org.test.suiblogexample.sui.contract.CommentDynamicField;

import java.util.*;
import java.math.*;
import java.util.function.*;

public class SuiArticleStateRetriever {

    private SuiJsonRpcClient suiJsonRpcClient;

    private Function<String, ArticleState.MutableArticleState> articleStateFactory;
    private BiFunction<ArticleState, BigInteger, CommentState.MutableCommentState> commentStateFactory;

    public SuiArticleStateRetriever(SuiJsonRpcClient suiJsonRpcClient,
                                  Function<String, ArticleState.MutableArticleState> articleStateFactory,
                                  BiFunction<ArticleState, BigInteger, CommentState.MutableCommentState> commentStateFactory
    ) {
        this.suiJsonRpcClient = suiJsonRpcClient;
        this.articleStateFactory = articleStateFactory;
        this.commentStateFactory = commentStateFactory;
    }

    public ArticleState retrieveArticleState(String objectId) {
        SuiMoveObjectResponse<Article> getObjectDataResponse = suiJsonRpcClient.getMoveObject(
                objectId, new SuiObjectDataOptions(true, true, true, true, true, true, true), Article.class
        );
        if (getObjectDataResponse.getData() == null) {
            return null;
        }
        Article article = getObjectDataResponse.getData().getContent().getFields();
        return toArticleState(article);
    }

    private ArticleState toArticleState(Article article) {
        ArticleState.MutableArticleState articleState = articleStateFactory.apply(article.getId().getId());
        articleState.setVersion(article.getVersion());
        articleState.setTitle(article.getTitle());
        articleState.setBody(article.getBody());
        articleState.setOwner(article.getOwner());
        articleState.setTags(new HashSet<>(Arrays.asList(article.getTags())));
        articleState.setTagsV2(new HashSet<>(Arrays.asList(article.getTagsV2())));
        if (article.getComments() != null) {
            String commentTableId = article.getComments().getFields().getId().getId();
            List<Comment> comments = getComments(commentTableId);
            for (Comment i : comments) {
                articleState.getComments().add(toCommentState(articleState, i));
            }
        }

        return articleState;
    }

    private CommentState toCommentState(ArticleState articleState, Comment comment) {
        CommentState.MutableCommentState commentState = commentStateFactory.apply(articleState, comment.getCommentSeqId());
        commentState.setCommenter(comment.getCommenter());
        commentState.setBody(comment.getBody());
        commentState.setOwner(comment.getOwner());
        return commentState;
    }

    private List<Comment> getComments(String commentTableId) {
        List<Comment> comments = new ArrayList<>();
        String cursor = null;
        while (true) {
            DynamicFieldPage commentFieldPage = suiJsonRpcClient.getDynamicFields(commentTableId, cursor, null);
            for (DynamicFieldInfo commentFieldInfo : commentFieldPage.getData()) {
                String fieldObjectId = commentFieldInfo.getObjectId();
                SuiMoveObjectResponse<CommentDynamicField> getCommentFieldResponse
                        = suiJsonRpcClient.getMoveObject(fieldObjectId, new SuiObjectDataOptions(true, true, true, true, true, true, true), CommentDynamicField.class);
                Comment comment = getCommentFieldResponse
                        .getData().getContent().getFields().getValue().getFields();
                comments.add(comment);
            }
            cursor = commentFieldPage.getNextCursor();
            if (!Page.hasNextPage(commentFieldPage)) {
                break;
            }
        }
        return comments;
    }

    
}

