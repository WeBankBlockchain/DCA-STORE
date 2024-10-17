package com.webank.dca.store.monitor;

import com.webank.dca.store.enums.ResponseEnum;
import com.webank.dca.store.utils.JsonUtils;
import com.webank.dca.store.vo.CommonResponse;
import com.webank.dca.store.vo.StoreRequest;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Order(10)
@Aspect
@Component
@Slf4j
public class WebLogMonitorAspect {


    @Pointcut("@annotation(com.webank.dca.store.monitor.UploadMonitor)")
    public void monitorLog() {}


    @Around(value = "monitorLog() && @annotation(uploadMonitor)", argNames= "uploadMonitor")
    public Object doAround(ProceedingJoinPoint proceedingJoinPoint, UploadMonitor uploadMonitor) throws Throwable {
        long startTime = System.currentTimeMillis();
        String seqNo = null;
        if(uploadMonitor.value().equals("upload")) {
            seqNo = (String) proceedingJoinPoint.getArgs()[0];
        }else{
            StoreRequest storeRequest = (StoreRequest) proceedingJoinPoint.getArgs()[0];
            seqNo = storeRequest.getSeqNo();
        }
        try {
            CommonResponse result = (CommonResponse) proceedingJoinPoint.proceed();
            MonitorEntry monitorEntry =
                    new MonitorEntry(
                            proceedingJoinPoint.getSignature().getName(),
                            seqNo,
                            result.getResponseCode(),
                            result.getResponseMessage(),
                            System.currentTimeMillis() - startTime);
            MonitorUtil.getMonitorLogger().info("[{}]", JsonUtils.toJson(monitorEntry));
            return result;
        } catch (Exception e) {
            ResponseEnum ee = ResponseEnum.parse(e.getMessage());
            MonitorEntry monitorEntry =
                    new MonitorEntry(
                            proceedingJoinPoint.getSignature().getName(),
                            seqNo,
                            ee.getResponseCode(),
                            ee.getMessage(),
                            System.currentTimeMillis() - startTime);
            MonitorUtil.getMonitorLogger().info("[{}]", JsonUtils.toJson(monitorEntry));
            throw e;
        }
    }
}
