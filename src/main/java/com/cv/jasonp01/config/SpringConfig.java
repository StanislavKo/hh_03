package com.cv.jasonp01.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import com.cv.jasonp01.service.AccountService;

@Configuration
@ComponentScan(basePackageClasses = {AccountService.class})
public class SpringConfig {
}
