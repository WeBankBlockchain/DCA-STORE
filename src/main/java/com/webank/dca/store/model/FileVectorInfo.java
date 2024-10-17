package com.webank.dca.store.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @author yuzhichu
 */
@Data
@NoArgsConstructor
public class FileVectorInfo {

    private long pkId;

    private String fileId;

    private int algorithm;

    private String feature;

    private String appId;

    private String collectionId;

    private String fileHash;

    private Date createTime;

    private Date updateTime;


}
