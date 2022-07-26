package io.horatius.farouk_university.repositories;

import io.horatius.farouk_university.dao.CourseCapacity;
import org.springframework.data.cassandra.repository.Query;
import org.springframework.data.cassandra.repository.ReactiveCassandraRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.UUID;

public interface CourseCapacityRepository extends ReactiveCassandraRepository<CourseCapacity, UUID> {
    @Query("UPDATE course_free_slots\n" +
            " SET capacity = capacity + 1\n" +
            " WHERE course_id = ?0;")
    public Mono<Void> incrementCapacityOfCourse(UUID courseId);
    public Flux<CourseCapacity> findAllByCapacityGreaterThan(int minimum);
    public Mono<CourseCapacity> findCourseFreeSlotsByCourseId(UUID courseId);
    /*
     * IN keyword to be used in Cassandra
     * https://stackoverflow.com/questions/26999098/is-the-in-relation-in-cassandra-bad-for-queries
     * https://ahappyknockoutmouse.wordpress.com/2014/11/12/246/
     */

}
