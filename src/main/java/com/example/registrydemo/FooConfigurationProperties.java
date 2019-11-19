package com.example.registrydemo;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "my.test")
@Data
public class FooConfigurationProperties {
    String name;
    String age;
}
