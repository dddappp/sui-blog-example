// <autogenerated>
//   This file was generated by dddappp code generator.
//   Any changes made to this file manually will be lost next time the file is regenerated.
// </autogenerated>

package org.test.suiblogexample.sui.contract.article;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

import org.test.suiblogexample.sui.contract.*;

import java.math.*;
import java.util.*;

@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class CommentAdded {
    private String id;

    private BigInteger version;

    private BigInteger commentSeqId;

    private String commenter;

    private String body;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public BigInteger getVersion() {
        return version;
    }

    public void setVersion(BigInteger version) {
        this.version = version;
    }

    public BigInteger getCommentSeqId() {
        return commentSeqId;
    }

    public void setCommentSeqId(BigInteger commentSeqId) {
        this.commentSeqId = commentSeqId;
    }

    public String getCommenter() {
        return commenter;
    }

    public void setCommenter(String commenter) {
        this.commenter = commenter;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    @Override
    public String toString() {
        return "CommentAdded{" +
                "id='" + id + '\'' +
                ", version=" + version +
                ", commentSeqId=" + commentSeqId +
                ", commenter=" + '\'' + commenter + '\'' +
                ", body=" + '\'' + body + '\'' +
                '}';
    }

}
