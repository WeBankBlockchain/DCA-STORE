package com.webank.dca.store.service;

import com.webank.dca.store.handler.WmVectorHandler;
import com.webank.dca.store.model.WmFileVector;
import com.webank.dca.store.vo.WmMatchVectorInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class WmQueryService {

    @Autowired
    private WmVectorHandler vectorHandler;


    public WmMatchVectorInfo getVectorByHash(String uniqueHash){
        WmFileVector wmFileVector = vectorHandler.getVectorByHash(uniqueHash);
        return wmFileVector == null ? new WmMatchVectorInfo("","") : new WmMatchVectorInfo(wmFileVector.getFeature(), wmFileVector.getUniqueId());
    }


}
