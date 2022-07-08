package com.tech.test.config;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Data
@Configuration
@ComponentScan(basePackages = {
        "com.tech.test.stepdefinitions",
        "com.tech.test.client",
        "com.tech.test.utils"})
@PropertySource("classpath:/config/local.properties")
public class TestConfig {

  @Bean
  public String favQuotesBaseUrl(@Value("${favqs.base.url}") String baseUrl) {
    return baseUrl;
  }

  @Bean
  public String accessToken(@Value("${access.token}") String accessToken) {
    return accessToken;
  }
}
