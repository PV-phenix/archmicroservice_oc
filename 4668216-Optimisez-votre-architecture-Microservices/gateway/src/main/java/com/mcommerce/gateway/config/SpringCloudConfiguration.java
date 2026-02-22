package com.mcommerce.gateway.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.factory.TokenRelayGatewayFilterFactory;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;

import com.mcommerce.gateway.filters.LoggingGatewayFilterFactory;


@Configuration
@EnableWebFluxSecurity
public class SpringCloudConfiguration {
	
//    @Bean
//    public Mono<HandlerFunction<ServerResponse>> getRoute(ServerRequest serverRequest) {
//    	return Mono.justOrEmpty(ServerResponse.ok().bodyValue("Hello from Gateway!"));
//    }
	
	@Autowired
	private TokenRelayGatewayFilterFactory filterFactory;
	
	@Bean
     RouteLocator gatewayRoutes(RouteLocatorBuilder builder, LoggingGatewayFilterFactory loggingFactory) {
        return builder.routes()
        		
		                .route("mproduits", r -> r.path("/Produits/**")
//		                .filters(f -> f.rewritePath("/service(?<segment>/?.*)", "$\\{segment}")
//		                          		.filter(loggingFactory.apply(new Config("My Custom Message", true, true)))
		                .filters(f -> f.filters(filterFactory.apply())
		                                .removeRequestHeader("Cookie"))                
		                .uri("http://localhost:9001"))
		                
		                .route("mcommandes", r -> r.path("/commandes/**")
				        .filters(f -> f.filters(filterFactory.apply())
		                                .removeRequestHeader("Cookie")) 
		                .uri("http://localhost:9002"))
		                
		                .route("mexpedition", r -> r.path("/expeditions/**")
		                .filters(f -> f.filters(filterFactory.apply())
		                                .removeRequestHeader("Cookie"))
		                .uri("http://localhost:9006"))
		                
		                .route("mpaiement", r -> r.path("/paiement/**")
		                .filters(f -> f.filters(filterFactory.apply())
		                                .removeRequestHeader("Cookie"))
		                .uri("http://localhost:9003"))
		                
		                .build();
    }
    
//    @Bean
//    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
//        http.authorizeHttpRequests(authorize -> authorize.anyRequest().authenticated())
//                .oauth2ResourceServer((oauth2) -> oauth2.jwt(Customizer.withDefaults()));
//        return http.build();
//    }
	
//    @Bean
//    public SecurityWebFilterChain springSecurityFilterChain(ServerHttpSecurity http) {
//        http.authorizeExchange(auth -> auth.anyExchange().authenticated())
//                .oauth2Login(withDefaults())
//                .oauth2ResourceServer((oauth2) -> oauth2.jwt(Customizer.withDefaults()));
//        http.csrf(ServerHttpSecurity.CsrfSpec::disable);
//        return http.build();
//    }
	

}

