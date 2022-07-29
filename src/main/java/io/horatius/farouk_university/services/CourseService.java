package io.horatius.farouk_university.services;

import com.datastax.oss.driver.api.core.uuid.Uuids;
import io.horatius.farouk_university.dao.CourseByCreator;
import io.horatius.farouk_university.dao.User;
import io.horatius.farouk_university.exceptions.InvalidCourseException;
import io.horatius.farouk_university.exceptions.UserAlreadyEnrolledException;
import io.horatius.farouk_university.models.Course;
import io.horatius.farouk_university.repositories.CourseByCreatorRepository;
import io.horatius.farouk_university.repositories.EnrollmentRepository;
import io.horatius.farouk_university.repositories.CourseCapacityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.security.Principal;
import java.util.UUID;

@Service
public class CourseService {
    @Autowired
    private CourseByCreatorRepository courseByCreatorRepository;
    @Autowired
    private CourseCapacityRepository courseCapacityRepository;
    @Autowired
    private EnrollmentRepository enrollmentRepository;

    public Mono<Course> createCourse(Course course, Mono<Principal> principal){
        var principalPlaceHolder =
                Mono.just(new User("laurence.krauss", "somethingfromnothing", "Lawrence", "Kraus"));
        var courseUuids = Uuids.timeBased();
        course.setCourseId(courseUuids);
        if(course.getCapacity() < 1 || course.getCapacity() > 100 ||
           course.getCourseName().length() > 100 || course.getCourseName().length() < 1){
            return Mono.error(new InvalidCourseException());
        }
        for(int i=0; i< course.getCapacity(); i++) {
            courseCapacityRepository.incrementCapacityOfCourse(courseUuids).subscribe();
        }
        return principalPlaceHolder.flatMap(p -> this.courseByCreatorRepository
                .save(new CourseByCreator(p.getName(),
                                          course.getCourseId() ,
                                          course.getCourseName(),
                                          course.getDescription(),
                                          course.getEnrollmentKey())))
                .log("Creating the course with " + courseUuids)
                .flatMap(c -> Mono.just(course));
    }

    /*
     * What should be done here is to filter all courses to fetch the courses and fetch the created courses by the user
     * and the courses that the user are enrolled in.
     * For fetching created users, it is relatively easy for fetching the created courses because will filter it according
     * to the partition key.
     * The challenging part is retrieving the enrolled courses, as the enrollments are present in other table and hence
     * we will return another flux of enrollments.
     *
     * First, we need to flatMap the enrollment to the courseId,
     *
     * In Reactive Spring, the fetched principal is always a Mono, so how do we filter to a flux using data from mono?
     * We have to wait for the mono to resolve (note: resolving isn't blocking) and then use flatMapMany to filter
     * a flux using the resolved user.
     * Courtesy of:
     * https://stackoverflow.com/questions/73110893/filtering-with-comparing-each-element-of-a-flux-to-a-single-mono/73111208#73111208
     */
    public Flux<CourseByCreator> getUserCourses(Mono<Principal> placeholder) {
        /*
         * Leaving the principal as a placeholder because Spring Security is not yet configured
         */
        var principal = Mono.just(new User("dibiasky", "whatifitsnotequal", "Kate", "Dibiasky"));

        Flux<CourseByCreator> allCourses = this.courseByCreatorRepository.findAll();
        var allCreatedCourses = principal
                .log("Fetching the created courses by user" + principal)
                .flatMapMany(p -> {
                    return  allCourses
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

        Flux<CourseByCreator> allEnrolledCourses = allCourses.filterWhen(course -> {
                    return allEnrolledCoursesIds.hasElement(course.getCourseId());
                    });

        return Flux.concat(allCreatedCourses, allEnrolledCourses);
    }

}
