package com.media.restaurant.exception;

import java.util.LinkedHashMap;
import java.util.Map;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "app")
@EnableConfigurationProperties
public class ExceptionConfigProperties {
  private Map<String, String> exceptionDetailsMap = new LinkedHashMap<>();

  public Map<String, String> getExceptionDetailsMap() {
    return exceptionDetailsMap;
  }

  public void setExceptionDetailsMap(Map<String, String> exceptionDetailsMap) {
    this.exceptionDetailsMap = exceptionDetailsMap;
  }
}
