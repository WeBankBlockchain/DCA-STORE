package com.webank.dca.store.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class WmFileVector {

    private long pkId;

    private String uniqueId;

    private int algorithmType;

    private String feature;

    private String appId;

    private String uniqueHash;

}
