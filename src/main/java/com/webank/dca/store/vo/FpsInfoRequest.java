package com.webank.dca.store.vo;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class FpsInfoRequest {
    @NotBlank
    private String seqNo;
    @NotBlank
    private String fpsId;
    @NotBlank
    private String fpsHash;
}
