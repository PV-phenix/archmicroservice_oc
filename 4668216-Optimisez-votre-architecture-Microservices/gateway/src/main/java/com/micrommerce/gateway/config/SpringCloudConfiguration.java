package com.micrommerce.gateway.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;

@Configuration
public class SpringCloudConfiguration {
	
	@Bean
    public RouteLocator gatewayRoutes(RouteLocatorBuilder builder) {
        return builder.routes()
                .route("produitsModule", r -> r.path("/Produits/**")
                        .uri("lb://microservice-produits")
                )

                .route("commandesModule", r -> r.path("/commandes/**")
                        .uri("lb://microservice-commandes")
                )
                .build();
    }

}
