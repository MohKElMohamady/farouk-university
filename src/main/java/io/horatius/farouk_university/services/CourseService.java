package io.horatius.farouk_university.services;

import io.horatius.farouk_university.dao.CourseByCreator;
import io.horatius.farouk_university.dao.User;
import io.horatius.farouk_university.repositories.CourseByCreatorRepository;
import io.horatius.farouk_university.repositories.EnrollmentRepository;
import io.horatius.farouk_university.repositories.CourseCapacityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.security.Principal;

@Service
public class CourseService {
    @Autowired
    private CourseByCreatorRepository courseByCreatorRepository;
    @Autowired
    private CourseCapacityRepository courseCapacityRepository;
    @Autowired
    private EnrollmentRepository enrollmentRepository;

    /*
     * How to filter
     * Courtesy of:
     * https://stackoverflow.com/questions/73110893/filtering-with-comparing-each-element-of-a-flux-to-a-single-mono/73111208#73111208
     */
    public Flux<CourseByCreator> getUserCourses(Principal placeholder) {
        /*
         * Leaving the principal as a placeholder because Spring Security is not yet configured
         */
        var principal = Mono.just(new User("dibiasky", "whatifitsnotequal", "Kate", "Dibiasky"));

        var allCreatedCourses = principal
                .log("Fetching the created courses by user" + principal)
                .flatMapMany(p -> {
                    return courseByCreatorRepository.findAll()
                            .filter(course -> course.getCourseCreator().equals(p.getUsername()));
                });

        var allEnrolledCoursesIds = principal.
                log("Fetching all enrollements of user " + principal)
                    .flatMapMany(p -> {
                    return enrollmentRepository.findAll().filter(enrollment -> enrollment
                            .getUserId()
                            .equals(p.getUsername()));
                     })
                .log("Mapping each ")
                .flatMap(enrollment -> Flux.just(enrollment.getCourseId()))
                .log();

        Flux<CourseByCreator> allEnrolledCourses = this.courseByCreatorRepository
                .findAll()
                    .filterWhen(course -> {
                    return allEnrolledCoursesIds.hasElement(course.getCourseId());
                    });

        return Flux.concat(allCreatedCourses, allEnrolledCourses);
    }

}
