// <autogenerated>
//   This file was generated by dddappp code generator.
//   Any changes made to this file manually will be lost next time the file is regenerated.
// </autogenerated>

package org.test.suiblogexample.sui.contract;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.github.wubuku.sui.bean.*;

import java.math.*;
import java.util.*;

@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class Article {

    private UID id;

    private Long offChainVersion;

    private String title;

    private String body;

    private BigInteger version;

    private Table comments;

    public UID getId() {
        return id;
    }

    public void setId(UID id) {
        this.id = id;
    }

    public Long getOffChainVersion() {
        return offChainVersion;
    }

    public void setOffChainVersion(Long offChainVersion) {
        this.offChainVersion = offChainVersion;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public BigInteger getVersion() {
        return version;
    }

    public void setVersion(BigInteger version) {
        this.version = version;
    }

    public Table getComments() {
        return comments;
    }

    public void setComments(Table comments) {
        this.comments = comments;
    }

    @Override
    public String toString() {
        return "Article{" +
                "id=" + id +
                ", offChainVersion=" + offChainVersion +
                ", title=" + '\'' + title + '\'' +
                ", body=" + '\'' + body + '\'' +
                ", version=" + version +
                ", comments=" + comments +
                '}';
    }
}
