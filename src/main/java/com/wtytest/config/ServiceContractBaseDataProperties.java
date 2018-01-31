package com.wtytest.config;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.Map;

@Getter
@Setter
@Configuration
@ToString
@ConfigurationProperties(prefix = "serviceContractBaseData")
public class ServiceContractBaseDataProperties {

    private static Map<String, ServiceContract> serviceContractBaseData;

    static {

    }

    private ServiceContract silver;
    private ServiceContract blue;
    private ServiceContract lightBlue;

    @PostConstruct
    private void initServiceContractBaseData() {
        serviceContractBaseData = new HashMap<>();
        serviceContractBaseData.put("SILVER", silver);
        serviceContractBaseData.put("BLUE", blue);
        serviceContractBaseData.put("LIGHT_BLUE", lightBlue);
    }

    public static class ServiceContract extends AbstractServiceContract {
    }

    @Getter
    @Setter
    @ToString
    private static abstract class AbstractServiceContract {
        private String shortName;
        private Map<String, String> coverages;
        private String description;
    }
}
