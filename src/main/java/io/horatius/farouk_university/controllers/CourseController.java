package io.horatius.farouk_university.controllers;

import io.horatius.farouk_university.dao.CourseByCreator;
import io.horatius.farouk_university.services.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;

import java.security.Principal;

@RestController
@RequestMapping(path = "/course")
public class CourseController {
    @Autowired
    private CourseService courseService;


    @GetMapping(value = "/my-courses")
    public Flux<CourseByCreator> getMine(Principal principal){
        return this.courseService
                .getUserCourses(principal);
    }

}
