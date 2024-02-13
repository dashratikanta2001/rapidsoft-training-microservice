package com.ms.userService.config;

import java.io.IOException;
import java.time.Duration;
import java.util.concurrent.TimeoutException;

import javax.xml.ws.WebServiceException;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

import io.github.resilience4j.circuitbreaker.CircuitBreaker;
import io.github.resilience4j.circuitbreaker.CircuitBreakerConfig;
import io.github.resilience4j.circuitbreaker.CircuitBreakerConfig.SlidingWindowType;
import io.github.resilience4j.circuitbreaker.CircuitBreakerRegistry;
import io.github.resilience4j.retry.RetryConfig;
import io.github.resilience4j.retry.RetryRegistry;

@Configuration
public class MyConfig {

	@Bean
	@LoadBalanced
	public RestTemplate restTemplate()
	{
		return new RestTemplate();
	}
	
	//Citcuit breaker bin config
	
	// Create a custom configuration for a CircuitBreaker
	CircuitBreakerConfig circuitBreakerConfig = CircuitBreakerConfig.custom()
	  .failureRateThreshold(50)
	  .automaticTransitionFromOpenToHalfOpenEnabled(true)
	  .waitDurationInOpenState(Duration.ofSeconds(6))
	  .permittedNumberOfCallsInHalfOpenState(3)
	  .minimumNumberOfCalls(10)
	  .slidingWindowType(SlidingWindowType.COUNT_BASED)
	  .slidingWindowSize(10)
	  .build();

	// Create a CircuitBreakerRegistry with a custom global configuration
	CircuitBreakerRegistry circuitBreakerRegistry = 
	  CircuitBreakerRegistry.of(circuitBreakerConfig);
	
	

	// Get or create a CircuitBreaker from the CircuitBreakerRegistry 
	// with the global default configuration
	CircuitBreaker circuitBreakerWithDefaultConfig = 
	  circuitBreakerRegistry.circuitBreaker("ratingHotelBreaker");
	
	
	RetryConfig config = RetryConfig.custom()
		    .maxAttempts(3)
		    .waitDuration(Duration.ofSeconds(10))
		    .build();
		    
		RetryRegistry registry = RetryRegistry.of(config);
	
}
