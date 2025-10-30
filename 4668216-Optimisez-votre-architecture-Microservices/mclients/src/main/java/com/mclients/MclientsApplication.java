package com.mclients;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients("com.mclients")
@EnableDiscoveryClient
public class MclientsApplication {

	public static void main(String[] args) {
		SpringApplication.run(MclientsApplication.class, args);
	}

}
