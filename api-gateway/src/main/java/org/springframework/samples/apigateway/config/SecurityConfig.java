package org.springframework.samples.apigateway.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.web.server.SecurityWebFilterChain;

@Configuration
@EnableWebFluxSecurity
public class SecurityConfig {
  @Bean
  public SecurityWebFilterChain springSecurityFilterChain(ServerHttpSecurity serverHttpSecurity) {
//    http.csrf(ServerHttpSecurity.CsrfSpec::disable)
//      .authorizeExchange(authorizeExchangeSpec -> authorizeExchangeSpec.pathMatchers("eureka/**")
//        .permitAll()
//        .anyExchange()
//        .authenticated())
//      .oauth2ResourceServer(ServerHttpSecurity.OAuth2ResourceServerSpec::jwt);
//    return http.build();
    serverHttpSecurity
      .csrf(ServerHttpSecurity.CsrfSpec::disable)
      .authorizeExchange(
        authorizeExchangeSpec -> authorizeExchangeSpec
          .pathMatchers("/eureka/**", "/actuator/**")
          .permitAll()
          .anyExchange()
          .authenticated()
      );
    serverHttpSecurity.oauth2ResourceServer(oauth2 -> oauth2
                        .jwt(Customizer.withDefaults())
                );
    return serverHttpSecurity.build();
  }
}
