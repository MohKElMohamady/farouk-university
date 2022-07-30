package io.horatius.farouk_university.services;


import io.horatius.farouk_university.models.User;
import io.horatius.farouk_university.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.ReactiveUserDetailsService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;
@Service
public class UserService implements ReactiveUserDetailsService {
    @Autowired
    private UserRepository userRepository;

    @Override
    public Mono<UserDetails> findByUsername(String username) {
        return userRepository.findUserByUsername(username).flatMap(scholar -> Mono.just(new User(scholar)));
    }
}
