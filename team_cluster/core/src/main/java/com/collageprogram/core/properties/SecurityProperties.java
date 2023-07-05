package com.collageprogram.core.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

/**
 * @Auther: binggoCoder
 * @Date: 2023/6/29
 */
@Data
@Component
@ConfigurationProperties(prefix = "security")
public class SecurityProperties {
    String [] excludes;
}
