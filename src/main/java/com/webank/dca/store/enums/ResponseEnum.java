package com.webank.dca.store.enums;

import com.webank.dca.store.constant.Constant;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ResponseEnum {
    Success(0, "000000000", "success"),
    Request_Timeout(1, Constant.SUB_SYSTEM_NO + "U00000001", "Timeout"),
    INTERNAL_ERROR(10500, Constant.SUB_SYSTEM_NO + "T00010500", "internal error"),
    No_App_Id(10051, Constant.SUB_SYSTEM_NO + "B00010051", "no app id"),
    UNIQUE_ID_EXSIT(10012, Constant.SUB_SYSTEM_NO + "B00010012", "unique id exist"),
    FILE_ID_EXSIT(10060, Constant.SUB_SYSTEM_NO + "B00010060", "file id exist"),
    FILE_HASH_EXSIT(10061, Constant.SUB_SYSTEM_NO + "B00010061", "file hash exist"),
    OVER_LIMIT(10063, Constant.SUB_SYSTEM_NO + "B00010063", "over appId capacity"),
    COMPUTE_ERROR(10022, Constant.SUB_SYSTEM_NO + "B00010022", "compute error"),
    FPS_DOWNLOAD_ERROR(10064, Constant.SUB_SYSTEM_NO + "B00010064", "fps download error"),
    COS_UPLOAD_ERROR(10065, Constant.SUB_SYSTEM_NO + "B00010065", "cos upload error");


    private int code;
    private String responseCode;
    private String message;

    public static ResponseEnum parse(String responseCode) {
        for (ResponseEnum e : ResponseEnum.values()) {
            if (e.getResponseCode().equals(responseCode)) {
                return e;
            }
        }
        return INTERNAL_ERROR;
    }
}
