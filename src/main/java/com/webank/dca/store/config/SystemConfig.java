package com.webank.dca.store.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * @author yuzhichu
 */
@Configuration
@ConfigurationProperties(prefix = "system")
@Data
public class SystemConfig {

    private String fileBufDir;

    private String fileStorage;

    private String computeUrl;

    private boolean isOverLimitEnable;
}
