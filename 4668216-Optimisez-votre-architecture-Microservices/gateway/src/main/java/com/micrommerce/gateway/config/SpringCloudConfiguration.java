package com.micrommerce.gateway.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.micrommerce.gateway.filters.LoggingGatewayFilterFactory;
import com.micrommerce.gateway.filters.LoggingGatewayFilterFactory.Config;


import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;

@Configuration
public class SpringCloudConfiguration {


    @Bean
    RouteLocator gatewayRoutes(RouteLocatorBuilder builder, LoggingGatewayFilterFactory loggingFactory) {
        return builder.routes()
        		
		                .route("produitsModule", r -> r.path("/Produits/**")
		                .filters(f -> f.rewritePath("/service(?<segment>/?.*)", "$\\{segment}")
		                          		.filter(loggingFactory.apply(new Config("My Custom Message", true, true)))
		                         )
		                .uri("lb://microservice-produits"))
		                
		                .route("commandesModule", r -> r.path("/commandes/**")
		                .uri("lb://microservice-commandes"))
		                
		                .route("expeditionModule", r -> r.path("/expeditions/**")
		                .uri("lb://mexpedition"))
		                
		                .build();
    }

}
