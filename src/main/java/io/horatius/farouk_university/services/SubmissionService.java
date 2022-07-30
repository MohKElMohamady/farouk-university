package io.horatius.farouk_university.services;

import com.datastax.oss.driver.api.core.uuid.Uuids;
import io.horatius.farouk_university.dao.SubmissionByAssignment;
import io.horatius.farouk_university.dao.Scholar;
import io.horatius.farouk_university.models.Submission;
import io.horatius.farouk_university.repositories.SubmissionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.security.Principal;
import java.util.UUID;

@Service
public class SubmissionService {
    @Autowired
    private SubmissionRepository submissionRepository;
    public Mono<Submission> submit(Submission submission, UUID courseId, UUID assignmentId, Mono<Principal> principal){
        Mono<Scholar> principalPlaceholder = Mono.just(new Scholar("dibiasky", "whatifitsnotequal", "Kate", "Dibiasky"));
        return principalPlaceholder.flatMap(p -> {
            return this.submissionRepository.save(new SubmissionByAssignment(assignmentId, Uuids.timeBased(), p.getUsername(), submission.getAnswer()))
                    .<Submission>flatMap(submissionByAssignment -> {
                        return Mono.just(new Submission.Builder()
                                .submissionId(Uuids.timeBased())
                                .assignment(assignmentId)
                                .answer(submission.getAnswer())
                                .user(p.getUsername())
                                .build());
                    });
        });
    }
    public Flux<Submission> getARandomSubmission(){
        return this.submissionRepository.findAll().take(1).flatMap(submissionByAssignment -> {
            return Flux.just(new Submission.Builder().submissionId(submissionByAssignment.getSubmissionId())
                                                     .answer(submissionByAssignment.getAnswer())
                                                     .build());
        });
    }
}
