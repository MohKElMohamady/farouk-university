package io.horatius.farouk_university.controllers;

import io.horatius.farouk_university.dao.CourseByCreatorAndId;
import io.horatius.farouk_university.models.Course;
import io.horatius.farouk_university.services.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.security.Principal;

@RestController
@RequestMapping(path = "/course")
public class CourseController {

    @Autowired
    private CourseService courseService;
    @GetMapping(path = "/available")
    public Flux<CourseByCreatorAndId> getAvailableCourses(Mono<Principal> principal){
        return this.courseService.getAvailableCourses(principal);
    }
    @GetMapping(path = "/my-courses")
    public Flux<CourseByCreatorAndId> getUserCourses(Mono<Principal> principal){
        return this.courseService
                .getUserCourses(principal);
    }

    @PostMapping()
    public Mono<Course> createCourse(@RequestBody Course course ,Mono<Principal> principal){
        return this.courseService.createCourse(course, principal);
    }
}
