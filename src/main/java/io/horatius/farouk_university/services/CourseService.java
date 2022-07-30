package io.horatius.farouk_university.services;

import com.datastax.oss.driver.api.core.uuid.Uuids;
import io.horatius.farouk_university.dao.CourseByCreatorAndId;
import io.horatius.farouk_university.dao.Scholar;
import io.horatius.farouk_university.dao.keys.CourseKey;
import io.horatius.farouk_university.exceptions.InvalidCourseException;
import io.horatius.farouk_university.models.Course;
import io.horatius.farouk_university.repositories.CourseRepository;
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
    private CourseRepository courseRepository;
    @Autowired
    private CourseCapacityRepository courseCapacityRepository;
    @Autowired
    private EnrollmentRepository enrollmentRepository;

    public Mono<Course> createCourse(Course course, Mono<Principal> principal){
        var principalPlaceHolder =
                Mono.just(new Scholar("laurence.krauss", "somethingfromnothing", "Lawrence", "Kraus"));
        var courseUuid = Uuids.timeBased();
        course.setCourseId(courseUuid);
        if(course.getCapacity() < 1 || course.getCapacity() > 100 ||
           course.getCourseName().length() > 100 || course.getCourseName().length() < 1){
            return Mono.error(new InvalidCourseException());
        }
        for(int i=0; i< course.getCapacity(); i++) {
            courseCapacityRepository.incrementCapacityOfCourse(courseUuid).subscribe();
        }
        return principalPlaceHolder.flatMap(p -> this.courseRepository
                .save(new CourseByCreatorAndId(new CourseKey(p.getUsername(), courseUuid),
                                               course.getCourseName(),
                                               course.getDescription(),
                                               course.getEnrollmentKey())))
                .log("Creating the course with " + courseUuid)
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
    public Flux<CourseByCreatorAndId> getUserCourses(Mono<Principal> placeholder) {
        /*
         * Leaving the principal as a placeholder because Spring Security is not yet configured
         */
        var principal = Mono.just(new Scholar("laurence.krauss", "whatifitsnotequal", "Kate", "Dibiasky"));
        /*
         * Because this flux is needed to fetch the created courses AND the enrolled courses, it makes sense to create
         * it once and to be used by the functionality of filtering created courses and enrolled courses.
         */
        Flux<CourseByCreatorAndId> allCourses = this.courseRepository.findAll();
        /*
         * Fetch the created courses by the user using the userId as a filter
         */
        var allCreatedCourses = principal
                .log("Fetching the created courses by user" + principal)
                .flatMapMany(p -> {
                    return  allCourses
                            .filter(course -> course.getCourseKey().getCourseCreator().equals(p.getUsername()));
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

        Flux<CourseByCreatorAndId> allEnrolledCourses = allCourses.filterWhen(course -> {
                    return allEnrolledCoursesIds.hasElement(course.getCourseKey().getCourseId());
                    });

        return Flux.concat(allCreatedCourses, allEnrolledCourses);
    }

    /*
     * Iterating through the first flux and looking in the second flux that match two properties
     * Ask if correct
     * https://stackoverflow.com/questions/49115135/map-vs-flatmap-in-reactor
     */
    public Flux<CourseByCreatorAndId> getAvailableCourses(Mono<Principal> principal){

        /*Flux<CourseByCreator> coursesById = this.courseCapacityRepository.findAll().filter(c -> c.getCapacity() >= 1)
                .log()
                .flatMap(courseCapacity -> {
                    System.out.println("Coming from capacity " + courseCapacity.getCourseId());
             return this.courseByCreatorRepository
                     .findAll()
                     .log()
                     .filter(courseByCreator -> {
                         System.out.println("Coming from courseCreator " + courseByCreator.getCourseId());
                         return courseByCreator.getCourseId() == courseCapacity.getCourseId();
                     });
            }
        ).log();*/
        Mono<Scholar> principalPlaceholder = Mono.just(new Scholar("dibiasky", "whatifitsnotequal", "Kate", "Dibiasky"));
        Flux<UUID> availableCoursesId = this.courseCapacityRepository
                .findAll()
                .filter(course -> course.getCapacity() >= 1)
                .flatMap(course -> Flux.just(course.getCourseId()));
        /*
         * https://stackoverflow.com/questions/60028533/difference-between-two-flux
         * https://stackoverflow.com/questions/69925549/filter-multiple-condition-reactor-flux-mono-filterwhen
         */

        return principalPlaceholder.flatMapMany(p -> {
           return this.courseRepository.findAll()
                   .filter(e -> !e.getCourseKey().getCourseCreator().equals(p.getUsername()))
                   .filterWhen(courseByCreatorAndId -> availableCoursesId.hasElement(courseByCreatorAndId.getCourseKey().getCourseId()));
        });

    }
    /*public Mono<Assignment> throwExceptionIfIsNotCreator(Mono<User> userCreatingAssignment, UUID courseId){
        return userCreatingAssignment.<Assignment>flatMap(user -> {
            return this.courseRepository.getCourseByCourseIdAndCreator(courseId, user.getUsername())
                    .switchIfEmpty(Mono.error(new NotAllowedToCreateAssignmentException()));
        });
    }*/
}
