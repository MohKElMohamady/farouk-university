package io.horatius.farouk_university.services;

import com.datastax.oss.driver.api.core.uuid.Uuids;
import io.horatius.farouk_university.dao.AssignmentByCourse;
import io.horatius.farouk_university.dao.Scholar;
import io.horatius.farouk_university.models.Assignment;
import io.horatius.farouk_university.repositories.AssignmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.security.Principal;
import java.util.UUID;

@Service
public class AssignmentService {
    @Autowired
    private AssignmentRepository assignmentRepository;
    @Autowired
    private CourseService courseService;

    public Mono<Assignment> createAssignment(Assignment a, Mono<Principal> principal){
        Mono<Scholar> principalPlaceholder = Mono.just(new Scholar("dibiasky", "whatifitsnotequal", "Kate", "Dibiasky"));

        /*
         * TODO: Figure out a way to throw an exception when the user who attempts to create the assignment is not the
         *  course creator.
         */
        return principalPlaceholder.<Assignment>flatMap(p -> {
                        return this.assignmentRepository.save(new AssignmentByCourse(Uuids.timeBased(), a.getCourse().getCourseId(), a.getDescription(),  p.getUsername()))
                                .<Assignment>flatMap(savedAssignment -> {
                                    return Mono.<Assignment>just(new Assignment.Builder()
                                            .course(savedAssignment.getCourseId())
                                            .assignmentId(savedAssignment.getAssignmentId())
                                            .description(savedAssignment.getDescription())
                                            .build());
                                });
                    });
    }

    public Flux<Assignment> getAssignmentsOfCourse(UUID courseId) {
        return assignmentRepository.findAllByCourseId(courseId)
                .flatMap(a -> {
                    return Flux.just(new Assignment.Builder()
                                                   .course(courseId)
                                                   .assignmentId(a.getAssignmentId())
                                                   .description(a.getDescription())
                                                   .build());
                });
    }
}
