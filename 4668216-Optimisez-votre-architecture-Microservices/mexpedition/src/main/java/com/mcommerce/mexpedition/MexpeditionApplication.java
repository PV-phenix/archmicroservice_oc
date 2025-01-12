package com.mcommerce.mexpedition;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication

@EnableFeignClients("com.mcommerce.mexpedition")
@EnableDiscoveryClient
public class MexpeditionApplication {

	public static void main(String[] args) {
		SpringApplication.run(MexpeditionApplication.class, args);
	}

}
