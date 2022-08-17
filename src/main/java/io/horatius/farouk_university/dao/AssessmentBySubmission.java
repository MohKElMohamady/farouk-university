package io.horatius.farouk_university.dao;

import io.horatius.farouk_university.models.Grade;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.cassandra.core.cql.Ordering;
import org.springframework.data.cassandra.core.cql.PrimaryKeyType;
import org.springframework.data.cassandra.core.mapping.CassandraType;
import org.springframework.data.cassandra.core.mapping.PrimaryKey;
import org.springframework.data.cassandra.core.mapping.PrimaryKeyColumn;
import org.springframework.data.cassandra.core.mapping.Table;

import java.util.UUID;
@AllArgsConstructor
@Data
@NoArgsConstructor
@Table(value = "assessments_by_submission")
public class AssessmentBySubmission {
    @PrimaryKeyColumn(value = "submission_id", ordinal = 0, type = PrimaryKeyType.PARTITIONED)
    @CassandraType(type = CassandraType.Name.TIMEUUID)
    private UUID submissionId;
    @PrimaryKeyColumn(type = PrimaryKeyType.CLUSTERED, ordinal = 1, ordering = Ordering.DESCENDING)
    @CassandraType(type = CassandraType.Name.TIMEUUID, userTypeName = "assignment_id")
    private UUID assessmentId;
    /*
     * Might be subject to change making a compound key, having both username and submission as key
     */
    @CassandraType(type = CassandraType.Name.TEXT, userTypeName = "username")
    private String username;
    @CassandraType(type = CassandraType.Name.INT, userTypeName = "grade")
    private int grade;
    @CassandraType(type = CassandraType.Name.TEXT, userTypeName = "comment")
    private String comment;

}
