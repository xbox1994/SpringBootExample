package com.wtytest.config;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Getter
@Setter
@ToString
@Component
@ConfigurationProperties
@PropertySource(value = "classpath:config/carItemMapping.properties", encoding = "UTF-8")
public class PreInspectionItemMappingProperties {
    private Map<String, String> defaultCarItemMapping = new HashMap<>();
    private Map<String, String> smartCarItemMapping = new HashMap<>();
}
