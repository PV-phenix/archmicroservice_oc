package com.mcommerce.gateway.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.mcommerce.gateway.filters.LoggingGatewayFilterFactory;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;


@Configuration
public class SpringCloudConfiguration {


    @Bean
     RouteLocator gatewayRoutes(RouteLocatorBuilder builder, LoggingGatewayFilterFactory loggingFactory) {
        return builder.routes()
        		
		                .route("mproduits", r -> r.path("/Produits/**")
//		                .filters(f -> f.rewritePath("/service(?<segment>/?.*)", "$\\{segment}")
//		                          		.filter(loggingFactory.apply(new Config("My Custom Message", true, true)))
//		                         )
		                .uri("http://localhost:9001"))
		                
		                .route("mcommandes", r -> r.path("/commandes/**")
		                .uri("lb://microservice-commandes"))
		                
		                .route("mexpedition", r -> r.path("/expeditions/**")
		                .uri("lb://mexpedition"))
		                
		                .build();
    }

}
