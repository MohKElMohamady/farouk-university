package io.horatius.farouk_university.configurations;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ReactiveAuthenticationManager;
import org.springframework.security.authentication.UserDetailsRepositoryReactiveAuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.method.configuration.EnableReactiveMethodSecurity;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.core.userdetails.MapReactiveUserDetailsService;
import org.springframework.security.core.userdetails.ReactiveUserDetailsService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.server.SecurityWebFilterChain;
import reactor.core.publisher.Mono;

@Configuration
@EnableReactiveMethodSecurity
@EnableWebFluxSecurity
public class SecurityConfig {
    /*
     https://docs.spring.io/spring-security/reference/reactive/configuration/webflux.html
     https://stackoverflow.com/questions/50015711/spring-security-webflux-body-with-authentication
     https://stackoverflow.com/questions/68417140/what-does-mapreactiveuserdetailsservice-do-in-spring-webflux-security-authentica
     */
    @Bean
    public SecurityWebFilterChain securityWebFilterChain(ServerHttpSecurity http){
        return http
                .authorizeExchange()
                .anyExchange()
                .authenticated()
                .and()
                .httpBasic()
                .and()
                .csrf(csrf -> csrf.disable())
                .build();
    }

    @Bean
    public ReactiveAuthenticationManager authenticationManager(ReactiveUserDetailsService userDetailsService,
                                                               PasswordEncoder passwordEncoder){
        UserDetailsRepositoryReactiveAuthenticationManager manager = new UserDetailsRepositoryReactiveAuthenticationManager(userDetailsService);
        manager.setPasswordEncoder(passwordEncoder);
        return manager;
    }


    /*
     * Courtesy of:
     * https://stackoverflow.com/questions/71843037/reactive-spring-security-authentication-using-r2dbc-mysql-connection
     * We have to provide a bean of ReactiveAuthentiationManager to Spring Boot so that it can use it for authentication
     * This was the answer of one of the people who got stuck, this is a detailed answer on how Spring Security does
     * the authentication itself.
     */
/*    @Bean
    public ReactiveAuthenticationManager reactiveAuthenticationManager(ReactiveUserDetailsService userDetailsService,
                                                               PasswordEncoder passwordEncoder) {
        return authentication ->
            userDetailsService.findByUsername(authentication.getPrincipal().toString())
                    .switchIfEmpty(Mono.error(new Exception()))
                    .flatMap(user -> {
                        final String username = authentication.getPrincipal().toString();
                        final CharSequence rawPassword = authentication.getCredentials().toString();
                        if (passwordEncoder.matches(rawPassword, user.getPassword())) {
                            System.out.println("User has been authentication " + username);
                            return Mono.just(new UsernamePasswordAuthenticationToken(username, user.getPassword(), user.getAuthorities()));
                        }
                        return Mono.just(new UsernamePasswordAuthenticationToken(username, authentication.getAuthorities()));
                    });

    }*/
        @Bean
    public PasswordEncoder noPasswordEncoder(){
        return NoOpPasswordEncoder.getInstance();
    }
}
