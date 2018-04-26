package com.gabrielebonadiman.child.config;

import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableFeignClients(basePackages = "com.gabrielebonadiman.child")
public class FeignConfiguration {

}
