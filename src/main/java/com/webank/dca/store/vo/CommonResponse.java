package com.webank.dca.store.vo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.webank.dca.store.enums.ResponseEnum;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CommonResponse<T> {

    private String responseCode = ResponseEnum.Success.getResponseCode();
    private String responseMessage = ResponseEnum.Success.getMessage();
    private String debugMessage;
    private String seqNo;
    private T responseData;

    public CommonResponse(
            String responseCode, String responseMessage, String debugMessage, T responseData) {
        this.responseCode = responseCode;
        this.responseMessage = responseMessage;
        this.debugMessage = debugMessage;
        this.responseData = responseData;
    }

    public CommonResponse(String seqNo) {
        this.seqNo = seqNo;
    }

    public static <T> CommonResponse<T> OK() {
        return new CommonResponse<T>();
    }

    public static <T> CommonResponse<T> OK(String seqNo) {
        CommonResponse<T> commonResponse = new CommonResponse<T>();
        commonResponse.setSeqNo(seqNo);
        return commonResponse;

    }

    public static <T> CommonResponse<T> data(T data) {
        CommonResponse<T> commonResponse = new CommonResponse<T>();
        commonResponse.setResponseData(data);
        return commonResponse;
    }

    public static <T> CommonResponse<T> error(String responseCode, String responseMessage) {
        return new CommonResponse<>(responseCode, responseMessage, null, null);
    }

    public static <T> CommonResponse<T> error(ResponseEnum res) {
        return new CommonResponse<>(res.getResponseCode(), res.getMessage(), null, null);
    }

    public static <T> CommonResponse<T> error(ResponseEnum res, String debugMessage) {
        return new CommonResponse<>(res.getResponseCode(), res.getMessage(), debugMessage, null);
    }

    @JsonIgnore
    public boolean isOK() {
        return responseCode.equals("0");
    }
}