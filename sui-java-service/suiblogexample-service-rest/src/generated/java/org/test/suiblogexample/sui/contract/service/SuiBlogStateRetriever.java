// <autogenerated>
//   This file was generated by dddappp code generator.
//   Any changes made to this file manually will be lost next time the file is regenerated.
// </autogenerated>

package org.test.suiblogexample.sui.contract.service;

import com.github.wubuku.sui.bean.*;
import com.github.wubuku.sui.utils.*;
import org.test.suiblogexample.domain.blog.*;
import org.test.suiblogexample.domain.*;
import org.test.suiblogexample.sui.contract.DomainBeanUtils;
import org.test.suiblogexample.sui.contract.Blog;

import java.util.*;
import java.math.*;
import java.util.function.*;

public class SuiBlogStateRetriever {

    private SuiJsonRpcClient suiJsonRpcClient;

    private Function<String, BlogState.MutableBlogState> blogStateFactory;

    public SuiBlogStateRetriever(SuiJsonRpcClient suiJsonRpcClient,
                                  Function<String, BlogState.MutableBlogState> blogStateFactory
    ) {
        this.suiJsonRpcClient = suiJsonRpcClient;
        this.blogStateFactory = blogStateFactory;
    }

    public BlogState retrieveBlogState(String objectId) {
        SuiMoveObjectResponse<Blog> getObjectDataResponse = suiJsonRpcClient.getMoveObject(
                objectId, new SuiObjectDataOptions(true, true, true, true, true, true, true), Blog.class
        );
        if (getObjectDataResponse.getData() == null) {
            return null;
        }
        Blog blog = getObjectDataResponse.getData().getContent().getFields();
        return toBlogState(blog);
    }

    private BlogState toBlogState(Blog blog) {
        BlogState.MutableBlogState blogState = blogStateFactory.apply(blog.getId().getId());
        blogState.setVersion(blog.getVersion());
        return blogState;
    }

    
}
