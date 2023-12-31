// <autogenerated>
//   This file was generated by dddappp code generator.
//   Any changes made to this file manually will be lost next time the file is regenerated.
// </autogenerated>

package org.test.suiblogexample.sui.contract.service;

import com.github.wubuku.sui.utils.SuiJsonRpcClient;
import org.test.suiblogexample.domain.*;
import org.test.suiblogexample.domain.tagv2.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.math.*;

@Service
public class SuiTagV2Service {

    @Autowired
    private TagV2StateRepository tagV2StateRepository;

    private SuiTagV2StateRetriever suiTagV2StateRetriever;

    @Autowired
    public SuiTagV2Service(SuiJsonRpcClient suiJsonRpcClient) {
        this.suiTagV2StateRetriever = new SuiTagV2StateRetriever(suiJsonRpcClient,
                id -> {
                    TagV2State.MutableTagV2State s = new AbstractTagV2State.SimpleTagV2State();
                    s.setId(id);
                    return s;
                }
        );
    }

    @Transactional
    public void updateTagV2State(String objectId) {
        TagV2State tagV2State = suiTagV2StateRetriever.retrieveTagV2State(objectId);
        if (tagV2State == null) {
            return;
        }
        tagV2StateRepository.merge(tagV2State);
    }

}

