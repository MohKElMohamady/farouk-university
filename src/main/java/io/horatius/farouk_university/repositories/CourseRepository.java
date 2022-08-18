package io.horatius.farouk_university.repositories;

import io.horatius.farouk_university.dao.CourseByCreatorAndId;
import io.horatius.farouk_university.dao.keys.CourseKey;
import org.springframework.data.cassandra.repository.Query;
import org.springframework.data.cassandra.repository.ReactiveCassandraRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

import java.util.UUID;

@Repository
public interface CourseRepository extends ReactiveCassandraRepository<CourseByCreatorAndId, CourseKey> {
    /*
     * The does not equal operator is not supported by Cassandra
     * https://stackoverflow.com/questions/46190703/cassandra-cql-select-operator
     */
    /*@Query("SELECT * FROM courses_by_creator_and_id WHERE course_creator != ?0")
    public Flux<CourseByCreatorAndId> findAllOthersCourses(String courseCreator);*/

    @Query("SELECT * FROM courses_by_creator_and_id WHERE course_id = ?0 AND course_creator = ?1")
    public Mono<CourseByCreatorAndId> getCourseByCourseIdAndCreator(UUID courseId, String userName);
    @Query("DELETE * FROM courses_by_creator_and_id WHERE course_id = ?0 AND course_creator = ?1")
    public Mono<CourseByCreatorAndId> deleteCourseByIdAndCreator(UUID courseId, String userName);
}
