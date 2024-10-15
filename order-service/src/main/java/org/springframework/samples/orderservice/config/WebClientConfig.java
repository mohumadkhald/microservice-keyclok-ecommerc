package org.springframework.samples.orderservice.config;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class WebClientConfig {
  @Bean
  @LoadBalanced
  public WebClient.Builder webClientBuilder(){
    return  WebClient.builder();
  } // if no eureka remove load and .builder and add build after builder
}
