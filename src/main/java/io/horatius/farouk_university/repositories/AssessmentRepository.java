package io.horatius.farouk_university.repositories;

import io.horatius.farouk_university.dao.AssessmentBySubmission;
import io.horatius.farouk_university.models.Assessment;
import org.springframework.data.cassandra.repository.ReactiveCassandraRepository;
import reactor.core.publisher.Flux;

import java.util.UUID;

public interface AssessmentRepository extends ReactiveCassandraRepository<AssessmentBySubmission, UUID> {
    public Flux<AssessmentBySubmission> findAssessmentBySubmissionId(UUID submissionId);
}
