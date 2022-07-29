package io.horatius.farouk_university.controllers;

import com.datastax.oss.driver.api.core.uuid.Uuids;
import io.horatius.farouk_university.dao.CourseByCreator;
import io.horatius.farouk_university.models.Course;
import io.horatius.farouk_university.repositories.CourseCapacityRepository;
import io.horatius.farouk_university.repositories.EnrollmentRepository;
import io.horatius.farouk_university.services.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.security.Principal;
import java.util.UUID;

@RestController
@RequestMapping(path = "/course")
public class CourseController {

    @Autowired
    private CourseService courseService;
    @GetMapping(path = "/my-courses")
    public Flux<CourseByCreator> getUserCourses(Mono<Principal> principal){
        return this.courseService
                .getUserCourses(principal);
    }

    @PostMapping()
    public Mono<Course> createCourse(@RequestBody Course course ,Mono<Principal> principal){
        return this.courseService.createCourse(course, principal);
    }
}
