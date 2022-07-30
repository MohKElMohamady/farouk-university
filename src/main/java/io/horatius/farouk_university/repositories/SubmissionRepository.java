package io.horatius.farouk_university.repositories;

import io.horatius.farouk_university.dao.SubmissionByAssignment;
import org.springframework.data.cassandra.repository.ReactiveCassandraRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;
@Repository
public interface SubmissionRepository extends ReactiveCassandraRepository<SubmissionByAssignment, UUID> {
}
