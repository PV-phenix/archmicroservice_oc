package com.client_ui.microservice;


import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.boot.system.JavaVersion;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Configuration;

@SpringBootTest
@Configuration

class ClientUiApplicationTests {

	@Test	
	void contextLoads() {
		assertEquals("25.0.1", JavaVersion.getJavaVersion().toString());
	}

}
