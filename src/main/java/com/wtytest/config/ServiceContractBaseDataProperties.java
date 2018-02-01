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
@PropertySource(value = "classpath:config/serviceContractBaseData.properties", encoding = "UTF-8")
public class ServiceContractBaseDataProperties {

    private Map<String, ServiceContract> serviceContractBaseData = new HashMap<>();

    @Getter
    @Setter
    @ToString
    public static class ServiceContract {
        private String shortName;
        private Map<String, String> coverages;
        private String description;
    }
}
