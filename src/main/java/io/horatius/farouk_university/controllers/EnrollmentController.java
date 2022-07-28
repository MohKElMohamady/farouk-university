package io.horatius.farouk_university.controllers;

import io.horatius.farouk_university.dao.EnrollmentByCourse;
import io.horatius.farouk_university.models.Enrollment;
import io.horatius.farouk_university.services.EnrollmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.security.Principal;
import java.util.UUID;

@RestController()
@RequestMapping(path = "/enroll")
public class EnrollmentController {

    @Autowired
    private EnrollmentService enrollmentService;

    @PostMapping("/course/{courseId}")
    public Flux<Enrollment> enrollIntoCourse(@PathVariable UUID courseId , Mono<Principal> principal){
        return enrollmentService.enrollUserToCourseWithFlux(courseId, principal);
    }

    @GetMapping("/course/{courseId}")
    public Flux<Enrollment> getEnroll(@PathVariable UUID courseId , Principal principal){
        return enrollmentService.getEnrollment(courseId, principal);
    }
}
