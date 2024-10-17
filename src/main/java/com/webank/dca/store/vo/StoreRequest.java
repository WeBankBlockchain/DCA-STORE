package com.webank.dca.store.vo;

import lombok.Data;

import java.util.List;

@Data
public class StoreRequest {

    private String seqNo;
    private List<StoreIdInfo> storeIdInfoList;
    private int fileType;
    private int algorithm;
    private String appId;
    private String collectionId;

}
