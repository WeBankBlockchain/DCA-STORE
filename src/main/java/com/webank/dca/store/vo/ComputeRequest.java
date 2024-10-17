package com.webank.dca.store.vo;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
public class ComputeRequest {

    private int algorithm;

    private int fileType;

    private MultipartFile fileContent;

    private String appId;

    private String collectionId;
}
