package com.webank.dca.store.exception;

import com.webank.dca.store.enums.ResponseEnum;
import com.webank.dca.store.vo.CommonResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

@ControllerAdvice
@Slf4j
public class ExceptionHandleAdvice {

    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public CommonResponse exceptionHandler(HttpServletRequest req, Exception e) {
        if(e instanceof FileIdExistException){
            log.warn(ResponseEnum.FILE_ID_EXSIT.getMessage());
            return CommonResponse.error(ResponseEnum.FILE_ID_EXSIT, ResponseEnum.FILE_ID_EXSIT.getMessage() + " , fileId = " + ((FileIdExistException) e).getMsg());
        }else if(e instanceof FileHashExistException){
            log.warn(ResponseEnum.FILE_HASH_EXSIT.getMessage());
            return CommonResponse.error(ResponseEnum.FILE_HASH_EXSIT, ResponseEnum.FILE_HASH_EXSIT.getMessage() + " , hash = " + ((FileHashExistException) e).getMsg());
        }else if(e instanceof ComputeException){
            log.error("OnError: {} ", ResponseEnum.COMPUTE_ERROR.getMessage());
            return CommonResponse.error(ResponseEnum.COMPUTE_ERROR, ResponseEnum.COMPUTE_ERROR.getMessage());
        }else if(e instanceof OverLimitException){
            log.warn(ResponseEnum.OVER_LIMIT.getMessage());
            return CommonResponse.error(ResponseEnum.OVER_LIMIT, ResponseEnum.OVER_LIMIT.getMessage());
        }
        log.error("OnError: Unknown Exception ",  e);
        return CommonResponse.error(ResponseEnum.INTERNAL_ERROR, e.getMessage());
    }
}
