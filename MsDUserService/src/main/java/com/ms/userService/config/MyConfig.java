package com.ms.userService.config;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientManager;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientProvider;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientProviderBuilder;
import org.springframework.security.oauth2.client.registration.ClientRegistrationRepository;
import org.springframework.security.oauth2.client.web.DefaultOAuth2AuthorizedClientManager;
import org.springframework.security.oauth2.client.web.OAuth2AuthorizedClientRepository;
import org.springframework.web.client.RestTemplate;

import com.ms.userService.config.intercepter.RestTemplateInterceptor;

import io.github.resilience4j.circuitbreaker.CircuitBreaker;
import io.github.resilience4j.circuitbreaker.CircuitBreakerConfig;
import io.github.resilience4j.circuitbreaker.CircuitBreakerConfig.SlidingWindowType;
import io.github.resilience4j.circuitbreaker.CircuitBreakerRegistry;
import io.github.resilience4j.retry.RetryConfig;
import io.github.resilience4j.retry.RetryRegistry;

@Configuration
public class MyConfig {
	
	@Autowired
	private ClientRegistrationRepository clientRegistrationRepository;
	
	@Autowired
	private OAuth2AuthorizedClientRepository oAuth2AuthorizedClientRepository;

	@Bean
	@LoadBalanced
	public RestTemplate restTemplate()
	{
		RestTemplate restTemplate = new RestTemplate();
		List<ClientHttpRequestInterceptor> interceptors = new ArrayList<>();
		
		interceptors.add(new RestTemplateInterceptor(manager(clientRegistrationRepository, oAuth2AuthorizedClientRepository)));
		restTemplate.setInterceptors(interceptors);
		
		return restTemplate;
	}
	
	// Declare the bean of OAuth2AuthorizedClientManager
	@Bean
	OAuth2AuthorizedClientManager manager(
			ClientRegistrationRepository clientRegistrationRepository,
			OAuth2AuthorizedClientRepository oAuth2AuthorizedClientRepository
			)
	{
		
		OAuth2AuthorizedClientProvider provider = OAuth2AuthorizedClientProviderBuilder.builder().clientCredentials().build();
		
		DefaultOAuth2AuthorizedClientManager defaultOAuth2AuthorizedClientManager = new DefaultOAuth2AuthorizedClientManager(clientRegistrationRepository, oAuth2AuthorizedClientRepository);
		
		defaultOAuth2AuthorizedClientManager.setAuthorizedClientProvider(provider);
		
		
		return defaultOAuth2AuthorizedClientManager;
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
