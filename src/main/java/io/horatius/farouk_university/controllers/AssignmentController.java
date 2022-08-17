package io.horatius.farouk_university.controllers;


import io.horatius.farouk_university.dao.AssignmentByCourse;
import io.horatius.farouk_university.models.Assessment;
import io.horatius.farouk_university.models.Assignment;
import io.horatius.farouk_university.repositories.AssignmentRepository;
import io.horatius.farouk_university.repositories.CourseRepository;
import io.horatius.farouk_university.services.AssignmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.security.Principal;
import java.util.UUID;

@RestController
@RequestMapping(path = "/assignment")
public class AssignmentController {
    @Autowired
    private AssignmentService assignmentService;
    @GetMapping(path = "/{courseId}")
    public Flux<Assignment> getAssignmentsOfCourse(@PathVariable UUID courseId){
        return this.assignmentService.getAssignmentsOfCourse(courseId).log();
    }

    @PostMapping(path = "/create/{courseId}")
    public Mono<Assignment> createAssignment(@RequestBody Assignment assignment, @PathVariable UUID courseId, Mono<Principal> principal){
        return this.assignmentService.createAssignment(assignment, principal);
    }

    @GetMapping("/{courseId}/all")
    public Flux<Assessment> fetchLatestAssessment(@PathVariable UUID courseId){
        return this.assignmentService.getUsersAnswerAndGradesOfAssignment(courseId);
    }
}
