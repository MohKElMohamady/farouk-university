package io.horatius.farouk_university.repositories;

import io.horatius.farouk_university.dao.AssignmentByCourse;
import org.springframework.data.cassandra.repository.ReactiveCassandraRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

import java.util.UUID;

@Repository
public interface AssignmentRepository extends ReactiveCassandraRepository<AssignmentByCourse, UUID> {
    public Flux<AssignmentByCourse> findAllByCourseId(UUID courseId);
}
