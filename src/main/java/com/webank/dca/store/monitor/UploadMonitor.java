package com.webank.dca.store.monitor;

import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD})
@Documented
public @interface UploadMonitor {
    String value() default "";
}
