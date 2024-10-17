package com.webank.dca.store.exception;


import com.webank.dca.store.utils.JsonUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;


@Aspect
@Component
@Slf4j
public class RequestLogAspect {

    @Pointcut("execution(public * com.webank.dca.store.controller..*.*(..))")
    public void requestPointCut() {}

    @AfterReturning(value = "requestPointCut()", returning = "o")
    public void afterRunningLog(Object o) {
        String responseJson = JsonUtils.toJson(o);
        responseJson = StringUtils.abbreviate(responseJson, 100);
        log.info("response is {}", responseJson);
    }
}
