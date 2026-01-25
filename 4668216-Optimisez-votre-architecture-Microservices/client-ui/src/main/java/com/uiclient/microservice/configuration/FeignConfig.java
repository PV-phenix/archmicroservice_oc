package com.uiclient.microservice.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import feign.auth.BasicAuthRequestInterceptor;


@Configuration
public class FeignConfig {
    @Bean
    BasicAuthRequestInterceptor mBasicAuthRequestInterceptor()

	{
	      return  new BasicAuthRequestInterceptor("pvaleri", "password");
	   }

}
