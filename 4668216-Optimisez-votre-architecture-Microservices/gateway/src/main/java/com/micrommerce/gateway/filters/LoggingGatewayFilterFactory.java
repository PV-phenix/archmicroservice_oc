package com.micrommerce.gateway.filters;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.stereotype.Component;

import reactor.core.publisher.Mono;

//import com.micrommerce.gateway.config.SpringCloudConfiguration.Config;

@Component	
public class LoggingGatewayFilterFactory extends AbstractGatewayFilterFactory<LoggingGatewayFilterFactory.Config>{

	 public static class Config {
	        private String baseMessage;
	        private boolean preLogger;
	        private boolean postLogger;
	        
			public String getBaseMessage() {
				return baseMessage;
			}
			public Config(String baseMessage, boolean preLogger, boolean postLogger) {
				super();
//				this.baseMessage = baseMessage;//Constructeur sans arguments
//				this.preLogger = preLogger;// les arguments sont definies dans application.properties
//				this.postLogger = postLogger;
			}
			public void setBaseMessage(String baseMessage) {
				this.baseMessage = baseMessage;
			}
			public boolean isPreLogger() {
				return preLogger;
			}
			public void setPreLogger(boolean preLogger) {
				this.preLogger = preLogger;
			}
			public boolean isPostLogger() {
				return postLogger;
			}
			public void setPostLogger(boolean postLogger) {
				this.postLogger = postLogger;
			}

		}
	final Logger logger =
    	      LoggerFactory.getLogger(LoggingGatewayFilterFactory.class);

    	    public LoggingGatewayFilterFactory() {
    	        super(Config.class);
    	    }

   @Override
   public GatewayFilter apply(Config config) {
    	        return (exchange, chain) -> {
    	            // Pre-processing
    	            if (config.isPreLogger()) {
    	                logger.info("Pre GatewayFilter logging: "
    	                  + config.getBaseMessage());
    	            }
    	            return chain.filter(exchange)
    	              .then(Mono.fromRunnable(() -> {
    	                  // Post-processing
    	                  if (config.isPostLogger()) {
    	                      logger.info("Post GatewayFilter logging: "
    	                        + config.getBaseMessage());
    	                  }
    	              }));
    	        };
    	    }
				
}
