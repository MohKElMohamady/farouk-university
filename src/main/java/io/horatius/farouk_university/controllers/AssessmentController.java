package io.horatius.farouk_university.controllers;

import io.horatius.farouk_university.models.Assessment;
import io.horatius.farouk_university.services.AssessmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import java.security.Principal;
import java.util.UUID;

@RestController
@RequestMapping(path = "{submissionId}/assess")
public class AssessmentController {
    @Autowired
    private AssessmentService assessmentService;

    @PostMapping()
    public Mono<Assessment> assess(@RequestBody Assessment assessment, @PathVariable UUID submissionId, Mono<Principal> principal){
        return this.assessmentService.assessASubmission(assessment, submissionId, principal);
    }

    @GetMapping()
    public Mono<Assessment> fetchLatestAssessment(@PathVariable UUID submissionId, Mono<Principal> principal){
        return this.assessmentService.fetchAssessmentBySubmission(submissionId, principal);
    }

}
