package com.uiclient.microservice.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.uiclient.microservice.exceptions.CustomErrorDecoder;

@Configuration
public class FeignExceptionConfig {
	
	   @Bean
	   public CustomErrorDecoder mCustomErrorDecoder(){
	       return new CustomErrorDecoder();
	   }

}
