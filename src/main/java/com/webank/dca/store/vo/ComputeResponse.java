package com.webank.dca.store.vo;

import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class ComputeResponse extends CommonResponse{

    private String vector;

}
