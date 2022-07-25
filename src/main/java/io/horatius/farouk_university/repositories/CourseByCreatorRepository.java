package io.horatius.farouk_university.repositories;

import io.horatius.farouk_university.dao.CourseByCreator;
import org.springframework.data.cassandra.repository.ReactiveCassandraRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
@Repository
public interface CourseByCreatorRepository extends ReactiveCassandraRepository<CourseByCreator, String> {
    public Flux<CourseByCreator> findAllByCourseCreator(String courseCreator);
}
