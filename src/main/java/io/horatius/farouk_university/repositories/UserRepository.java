package io.horatius.farouk_university.repositories;


import io.horatius.farouk_university.dao.Scholar;
import org.springframework.data.cassandra.repository.ReactiveCassandraRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;
@Repository
public interface UserRepository extends ReactiveCassandraRepository<Scholar, String> {
    Mono<Scholar> findUserByUsername(String username);
}
