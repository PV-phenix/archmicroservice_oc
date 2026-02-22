package com.mcommerce.gateway;




import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.WebSession;

import reactor.core.publisher.Mono;

@SpringBootApplication
@EnableDiscoveryClient
@RestController
public class GatewayApplication {
	
	
	
//	private static final Logger LOGGER = LoggerFactory.getLogger(GatewayApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(GatewayApplication.class, args);
	}
	
//
//	   @GetMapping(value = "/token")
//	   public Mono<String> getHome(@RegisteredOAuth2AuthorizedClient OAuth2AuthorizedClient authorizedClient) {
//	      return Mono.just(authorizedClient.getAccessToken().getTokenValue());
//	   }
	   
	    @GetMapping("/")
	    public Mono<String> index(WebSession session) {
	        return Mono.just("N° de session: "+ session.getId());
	    }
}
