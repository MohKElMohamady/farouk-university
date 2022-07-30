package io.horatius.farouk_university.controllers;

import io.horatius.farouk_university.dao.SubmissionByAssignment;
import io.horatius.farouk_university.models.Submission;
import io.horatius.farouk_university.services.SubmissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.security.Principal;
import java.util.UUID;

@RestController
@RequestMapping(path = "/{courseId}/{assignmentId}/submit")
public class SubmissionController {
    @Autowired
    private SubmissionService submissionService;
    @PostMapping()
    public Mono<Submission> submit(@RequestBody Submission submission, @PathVariable UUID courseId,
                                               @PathVariable UUID assignmentId , Mono<Principal> principal){
        return this.submissionService.submit(submission, courseId, assignmentId, principal);
    }

    @GetMapping()
    public Flux<Submission> getARandomSubmission(@PathVariable UUID courseId, @PathVariable UUID assignmentId){
        return this.submissionService.getARandomSubmission();
    }
}
