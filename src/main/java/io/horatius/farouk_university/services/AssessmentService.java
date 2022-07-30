package io.horatius.farouk_university.services;

import com.datastax.oss.driver.api.core.uuid.Uuids;
import io.horatius.farouk_university.dao.AssessmentBySubmission;
import io.horatius.farouk_university.dao.Scholar;
import io.horatius.farouk_university.models.Assessment;
import io.horatius.farouk_university.repositories.AssessmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import reactor.core.publisher.Mono;

import java.security.Principal;
import java.util.UUID;

@Service
public class AssessmentService {
    @Autowired
    private AssessmentRepository assessmentRepository;

    public Mono<Assessment> assessASubmission(@RequestBody Assessment assessment,UUID submissionId ,Mono<Principal> principal){
        Mono<Scholar> principalPlaceHolder = Mono.just(new Scholar());
                return principalPlaceHolder.flatMap(p -> {
                    return this.assessmentRepository.save(new AssessmentBySubmission(assessment.getSubmission().getSubmissionId(), Uuids.timeBased(), p.getUsername() ,assessment.getGrade(), assessment.getComment()))
                            .flatMap(assessmentBySubmission -> {
                                return Mono.just(new Assessment.Builder()
                                                               .assignmentId(assessmentBySubmission.getAssessmentId())
                                                               .submission(assessmentBySubmission.getSubmissionId())
                                                               .grade(assessmentBySubmission.getGrade())
                                                               .comment(assessmentBySubmission.getComment())
                                                               .build());
                            });
                });

    }
    /*
     *
     */
    public Mono<Assessment> fetchAssessmentBySubmission(UUID submissionId , Mono<Principal> principal){
        Mono<Scholar> principalPlaceholder = Mono.just(new Scholar());
        return principalPlaceholder.flatMap(p -> {
            return this.assessmentRepository.findAssessmentBySubmissionId(submissionId)
                                            .filter(assessmentBySubmission -> {
                                                return assessmentBySubmission.getUsername().equals( p.getUsername());})
                                            .flatMap(assessmentBySubmission -> {
                                                    return Mono.just(new Assessment.Builder()
                                                            .assignmentId(assessmentBySubmission.getAssessmentId())
                                                            .submission(assessmentBySubmission.getSubmissionId())
                                                            .grade(assessmentBySubmission.getGrade())
                                                            .comment(assessmentBySubmission.getComment())
                                                            .build());})
                                            .next();
            });
    }
}
