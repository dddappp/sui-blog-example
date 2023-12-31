package org.test.suiblogexample.sui.contract.service;

import com.github.wubuku.sui.bean.ObjectChange;
import com.github.wubuku.sui.bean.SuiTransactionBlockResponse;
import com.github.wubuku.sui.bean.SuiTransactionBlockResponseOptions;
import com.github.wubuku.sui.utils.SuiJsonRpcClient;
import org.test.suiblogexample.sui.contract.ContractConstants;
import org.test.suiblogexample.sui.contract.MoveObjectIdGeneratorObject;
import org.test.suiblogexample.sui.contract.SuiPackage;
import org.test.suiblogexample.sui.contract.repository.MoveObjectIdGeneratorObjectRepository;
import org.test.suiblogexample.sui.contract.repository.SuiPackageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.concurrent.atomic.AtomicReference;

@Service
public class MoveObjectIdGeneratorObjectService {
    @Autowired
    private MoveObjectIdGeneratorObjectRepository moveObjectIdGeneratorObjectRepository;

    @Autowired
    private SuiPackageRepository suiPackageRepository;

    @Autowired
    private SuiJsonRpcClient suiJsonRpcClient;

    @Value("${sui.contract.package-publish-transaction}")
    private String packagePublishTransaction;

    @Transactional
    public void initMoveObjectIdGeneratorObjects() {
        boolean showInput = false;
        boolean showRawInput = false;
        boolean showEffects = false;
        boolean showEvents = true;
        boolean showObjectChanges = true;
        boolean showBalanceChanges = false;
        SuiTransactionBlockResponse suiTransactionBlockResponse = suiJsonRpcClient.getTransactionBlock(
                packagePublishTransaction,
                new SuiTransactionBlockResponseOptions(showInput, showRawInput, showEffects, showEvents, showObjectChanges, showBalanceChanges)
        );

        AtomicReference<String> packageIdRef = new AtomicReference<>();
        ObjectChange[] objectChanges = suiTransactionBlockResponse.getObjectChanges();
        if (objectChanges == null) {
            System.out.println("No object changes found in SuiTransactionBlockResponse");
            return;
        }
        Arrays.stream(objectChanges).filter(
                c -> c instanceof ObjectChange.Published
        ).findFirst().ifPresent(c -> {
            ObjectChange.Published published = (ObjectChange.Published) c;
            //System.out.println(published);
            packageIdRef.set(published.getPackageId());
            saveDefaultSuiPackageIfNotExists(
                    published.getPackageId(),
                    null //todo publish.getDigest(),
            );
        });

        //System.out.println("package Id: " + packageIdRef.get());
        String packageId = packageIdRef.get();
        String[] idGeneratorDataObjTypes = ContractConstants.getMoveObjectIdGeneratorObjectTypes(packageId);
        Arrays.stream(objectChanges).filter(
                c -> c instanceof ObjectChange.Created
        ).forEach(c -> {
            ObjectChange.Created objectCreated = (ObjectChange.Created) c;
            int idx = objectCreated.getObjectType().indexOf("::");
            if (objectCreated.getObjectType().substring(0, idx).equals(packageId)) {
                if (Arrays.stream(idGeneratorDataObjTypes).anyMatch(t -> t.equals(objectCreated.getObjectType()))) {
                    saveMoveObjectIdGeneratorObjectIfNotExists(
                            objectCreated.getObjectType(), objectCreated.getObjectId());
                }
            }
        });
    }

    private void saveDefaultSuiPackageIfNotExists(String packageId, String publisher) {
        if (suiPackageRepository.findById(ContractConstants.DEFAULT_SUI_PACKAGE_NAME).isPresent()) {
            return;
        }
        SuiPackage suiPackage = new SuiPackage();
        suiPackage.setName(ContractConstants.DEFAULT_SUI_PACKAGE_NAME);
        suiPackage.setObjectId(packageId);
        suiPackage.setPublisher(publisher);

        suiPackageRepository.save(suiPackage);
    }

    private void saveMoveObjectIdGeneratorObjectIfNotExists(String suiObjectType, String suiObjectId) {
        int idx = suiObjectType.indexOf("::");
        if (idx < 0) {
            throw new IllegalArgumentException("Invalid sui object type: " + suiObjectType);
        }
        String objectName = suiObjectType.substring(idx + 2);
        if (moveObjectIdGeneratorObjectRepository.findById(objectName).isPresent()) {
            return;
        }
        MoveObjectIdGeneratorObject moveObjectIdGeneratorObject = new MoveObjectIdGeneratorObject();
        moveObjectIdGeneratorObject.setName(objectName);
        moveObjectIdGeneratorObject.setObjectType(suiObjectType);
        moveObjectIdGeneratorObject.setObjectId(suiObjectId);
        moveObjectIdGeneratorObjectRepository.save(moveObjectIdGeneratorObject);
    }
}
