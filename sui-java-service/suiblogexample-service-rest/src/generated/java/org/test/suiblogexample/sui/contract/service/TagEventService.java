// <autogenerated>
//   This file was generated by dddappp code generator.
//   Any changes made to this file manually will be lost next time the file is regenerated.
// </autogenerated>

package org.test.suiblogexample.sui.contract.service;

import com.github.wubuku.sui.bean.EventId;
import com.github.wubuku.sui.bean.Page;
import com.github.wubuku.sui.bean.PaginatedMoveEvents;
import com.github.wubuku.sui.bean.SuiMoveEventEnvelope;
import com.github.wubuku.sui.utils.SuiJsonRpcClient;
import org.test.suiblogexample.domain.tag.AbstractTagEvent;
import org.test.suiblogexample.sui.contract.ContractConstants;
import org.test.suiblogexample.sui.contract.DomainBeanUtils;
import org.test.suiblogexample.sui.contract.SuiPackage;
import org.test.suiblogexample.sui.contract.tag.TagCreated;
import org.test.suiblogexample.sui.contract.repository.TagEventRepository;
import org.test.suiblogexample.sui.contract.repository.SuiPackageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class TagEventService {

    @Autowired
    private SuiPackageRepository suiPackageRepository;

    @Autowired
    private SuiJsonRpcClient suiJsonRpcClient;

    @Autowired
    private TagEventRepository tagEventRepository;

    @Transactional
    public void updateStatusToProcessed(AbstractTagEvent event) {
        event.setStatus("D");
        tagEventRepository.save(event);
    }

    @Transactional
    public void pullTagCreatedEvents() {
        String packageId = getDefaultSuiPackageId();
        if (packageId == null) {
            return;
        }
        int limit = 1;
        EventId cursor = getTagCreatedEventNextCursor();
        while (true) {
            PaginatedMoveEvents<TagCreated> eventPage = suiJsonRpcClient.queryMoveEvents(
                    packageId + "::" + ContractConstants.TAG_MODULE_TAG_CREATED,
                    cursor, limit, false, TagCreated.class);

            if (eventPage.getData() != null && !eventPage.getData().isEmpty()) {
                cursor = eventPage.getNextCursor();
                for (SuiMoveEventEnvelope<TagCreated> eventEnvelope : eventPage.getData()) {
                    saveTagCreated(eventEnvelope);
                }
            } else {
                break;
            }
            if (!Page.hasNextPage(eventPage)) {
                break;
            }
        }
    }

    private EventId getTagCreatedEventNextCursor() {
        AbstractTagEvent lastEvent = tagEventRepository.findFirstTagCreatedByOrderBySuiTimestampDesc();
        return lastEvent != null ? new EventId(lastEvent.getSuiTxDigest(), lastEvent.getSuiEventSeq() + "") : null;
    }

    private void saveTagCreated(SuiMoveEventEnvelope<TagCreated> eventEnvelope) {
        AbstractTagEvent.TagCreated tagCreated = DomainBeanUtils.toTagCreated(eventEnvelope);
        if (tagEventRepository.findById(tagCreated.getTagEventId()).isPresent()) {
            return;
        }
        tagEventRepository.save(tagCreated);
    }


    private String getDefaultSuiPackageId() {
        return suiPackageRepository.findById(ContractConstants.DEFAULT_SUI_PACKAGE_NAME)
                .map(SuiPackage::getObjectId).orElse(null);
    }
}
