package io.horatius.farouk_university.services;

import com.datastax.oss.driver.api.core.uuid.Uuids;
import io.horatius.farouk_university.dao.EnrollmentByCourse;
import io.horatius.farouk_university.dao.Scholar;
import io.horatius.farouk_university.exceptions.MaximumUsersEnrolledException;
import io.horatius.farouk_university.exceptions.UserAlreadyEnrolledException;
import io.horatius.farouk_university.models.Enrollment;
import io.horatius.farouk_university.repositories.CourseCapacityRepository;
import io.horatius.farouk_university.repositories.EnrollmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.security.Principal;
import java.util.UUID;

@Service
public class EnrollmentService {
    @Autowired
    private EnrollmentRepository enrollmentRepository;
    @Autowired
    private CourseCapacityRepository courseCapacityRepository;
    public Mono<Enrollment> enrollUserToCourseWithMono(UUID courseId, Mono<Principal> principle){
        Mono<Scholar> p = Mono.just(new Scholar("dibiasky", "whatifitsnotequal", "Kate", "Dibiasky"));
        this.courseCapacityRepository.findCourseFreeSlotsByCourseId(courseId)
                .flatMap(courseCapacity -> {
                    if (courseCapacity.getCapacity() > 1)
                        return Mono.error(new MaximumUsersEnrolledException());
                    return Mono.empty();
                });
        return p.flatMap(userToBeEnrolled -> {
                    /*
                     * Fetch the reactive stream and filter it with the partition key
                     */
                    return this.enrollmentRepository.findEnrollmentByCourseIdSquared(courseId)
                            /*
                             * Map the enrollment of the enrollment to the enrolled user id
                             */
                            .flatMap(e -> Mono.just(e.getUserId()))
                            /*
                             * Then filter the result with the username to see if he is already enrolled
                             */
                            .filter(e -> e.equals(userToBeEnrolled.getUsername()));
                })
                .<Enrollment>flatMap(e -> {
                    /*
                     * If it is zero, an empty mono will be mapped so that the switchMap is activated.
                     */
                        return Mono.error(new UserAlreadyEnrolledException());
                })
                .switchIfEmpty(
                p.flatMap(user -> this.enrollmentRepository.save(new EnrollmentByCourse(courseId, user.getUsername(), Uuids.timeBased()))
                                                                        .flatMap(e -> Mono.just(new Enrollment.Builder()
                                                                                                              .course(e.getCourseId())
                                                                                                              .enrolledUser(e.getUserId())
                                                                                                              .enrollNow()
                                                                                                              .build())))
        );
    }
    /*
     * This method will enroll a user to a course, but initially it has to check if the user is already enrolled.
     * If the user is already enrolled, a UserAlreadyEnrolledException will be thrown.
     * Otherwise, the user will be enrolled successfully.
     * Throughout this method, Flux are used which is surprisingly faster (No idea how)
     */
    public Flux<Enrollment> enrollUserToCourseWithFlux(UUID courseId, Mono<Principal> principle){
        Mono<Scholar> p = Mono.just(new Scholar("dibiasky", "whatifitsnotequal", "Kate", "Dibiasky"));
        this.courseCapacityRepository.findCourseFreeSlotsByCourseId(courseId)
                .flatMap(courseCapacity -> {
                    if (courseCapacity.getCapacity() > 1)
                        return Mono.error(new MaximumUsersEnrolledException());
                    return Mono.empty();
                });
        return p.flatMapMany(userToBeEnrolled -> {
            /*
             * Fetch the reactive stream and filter it with the partition key
             */
            return this.enrollmentRepository.findEnrollmentByCourseId(courseId)
                    .filter(e -> e.getUserId().equals(userToBeEnrolled.getUsername()))
                    .count();
        }).next()
          .flatMapMany(e -> {
                    /*
                     * If it is zero, an empty mono will be mapped so that the switchIfEmpty is activated.
                     */
                    if(e == 0) {
                        return Mono.<EnrollmentByCourse>empty();
                    }else{
                        /*
                         * If there count is more than zero, the user is already enrolled.
                         * An exception will be thrown.
                         */
                        return Mono.error(new UserAlreadyEnrolledException());
                    }
                })
          /*
           *
           */
          .switchIfEmpty(p.flatMapMany(userToBeEnrolled -> this.enrollmentRepository
                  .save(new EnrollmentByCourse(courseId, userToBeEnrolled.getUsername(), Uuids.timeBased()))))
          .flatMap((EnrollmentByCourse e) -> Flux.just(new Enrollment.Builder()
                                     .enrolledUser(e.getUserId())
                                     .course(e.getCourseId())
                                     .build())
          );
    }
    public Flux<Enrollment> getEnrollment(UUID enrollment, Mono<Principal> principal){
        Scholar p = new Scholar("dibiasky", "whatifitsnotequal", "Kate", "Dibiasky");
        return this.enrollmentRepository.findEnrollmentByCourseId(enrollment).flatMap(
                enrollmentByCourse -> {
                    return Mono.just(new Enrollment.Builder().enrolledUser(p.getName()).build());
                }
        );
    }
}
