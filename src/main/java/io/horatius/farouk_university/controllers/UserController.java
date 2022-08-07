package io.horatius.farouk_university.controllers;


import io.horatius.farouk_university.dao.User;
import io.horatius.farouk_university.repositories.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.ReactiveSecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.web.reactive.function.server.ServerRequest;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.security.Principal;

@RestController
@RequestMapping(path = "/user")
@CrossOrigin
@Slf4j
public class UserController {
    @Autowired
    public UserRepository userRepository;
    @GetMapping(path = "{username}")
    public Mono<UserDetails> getUser(@PathVariable String username){
//        return userRepository.findUserByUsername(username);
        return Mono.empty();
    }
    /*
     * How to retrieve user details from Reactive Spring Security
     * https://stackoverflow.com/questions/52650382/spring-security-webflux-how-to-make-serverwebexchange-return-pre-authentication
     * https://spring.io/blog/2017/11/01/spring-security-5-0-0-rc1-released
     */
    @GetMapping()
    public Mono<Object> getUser(ServerHttpRequest serverRequest){
       return ReactiveSecurityContextHolder.getContext().flatMap(s ->  Mono.just(s.getAuthentication())).flatMap(e -> {
            log.info("This is how we get the username " + ((UserDetails)e.getPrincipal()).getUsername());
            return Mono.just(((UserDetails)e.getPrincipal()).getUsername());
        });
    }
}
