package com.webank.dca.store.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class WmComputeTask {

    private long pkId;

    private String uniqueHash;

    private String watermarkFileHash;

    private String uniqueId;

    private String appId;

    @JsonIgnore
    private byte[] file;

    private String seqNo;

    private int algorithm;

    private String vector;

}
