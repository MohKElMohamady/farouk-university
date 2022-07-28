package io.horatius.farouk_university.repositories;

import io.horatius.farouk_university.dao.EnrollmentByCourse;
import org.springframework.data.cassandra.repository.Query;
import org.springframework.data.cassandra.repository.ReactiveCassandraRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.UUID;

public interface EnrollmentRepository extends ReactiveCassandraRepository<EnrollmentByCourse, UUID> {
    @Query("SELECT * FROM enrollments_by_user where course_id = ?0")
    public Flux<EnrollmentByCourse> findEnrollmentByCourseId(UUID courseId);
    @Query("SELECT * FROM enrollments_by_user where course_id = ?0")
    public Mono<EnrollmentByCourse> findEnrollmentByCourseIdSquared(UUID courseId);
}
