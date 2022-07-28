package io.horatius.farouk_university.configurations;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableReactiveMethodSecurity;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.web.server.SecurityWebFilterChain;

@Configuration
@EnableReactiveMethodSecurity
@EnableWebFluxSecurity
public class SecurityConfig {
    /*
     https://docs.spring.io/spring-security/reference/reactive/configuration/webflux.html
     https://stackoverflow.com/questions/50015711/spring-security-webflux-body-with-authentication
     */
    @Bean
    public SecurityWebFilterChain securityWebFilterChain(ServerHttpSecurity http){
        return http
                .csrf(csrf -> csrf.disable())
                .authorizeExchange()
                .anyExchange()
                .permitAll()
                .and()
                .build();

//                .anyExchange()
//                .authenticated().and().authenticationManager();
    }
}
