package com.wtytest.config;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

@Getter
@Setter
@Configuration
@ToString
@ConfigurationProperties(prefix = "carItemMapping")
public class PreInspectionItemMappingProperties {
    private Map<String, String> defaultCarItemMapping = new HashMap<>();
    private Map<String, String> smartCarItemMapping = new HashMap<>();
}
