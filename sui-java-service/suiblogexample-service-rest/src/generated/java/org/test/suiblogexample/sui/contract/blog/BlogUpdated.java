// <autogenerated>
//   This file was generated by dddappp code generator.
//   Any changes made to this file manually will be lost next time the file is regenerated.
// </autogenerated>

package org.test.suiblogexample.sui.contract.blog;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

import org.test.suiblogexample.sui.contract.*;

import java.math.*;
import java.util.*;

@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class BlogUpdated {
    private String id;

    private BigInteger version;

    private String name;

    private String[] articles;

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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String[] getArticles() {
        return articles;
    }

    public void setArticles(String[] articles) {
        this.articles = articles;
    }

    @Override
    public String toString() {
        return "BlogUpdated{" +
                "id='" + id + '\'' +
                ", version=" + version +
                ", name=" + '\'' + name + '\'' +
                ", articles=" + Arrays.toString(articles) +
                '}';
    }

}
