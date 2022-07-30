package io.horatius.farouk_university.dao;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.cassandra.core.cql.PrimaryKeyType;
import org.springframework.data.cassandra.core.mapping.CassandraType;
import org.springframework.data.cassandra.core.mapping.PrimaryKey;
import org.springframework.data.cassandra.core.mapping.PrimaryKeyColumn;
import org.springframework.data.cassandra.core.mapping.Table;
import java.util.UUID;

@AllArgsConstructor
@Data
@NoArgsConstructor
@Table(value = "submission_by_assignment")
public class SubmissionByAssignment {
    @PrimaryKeyColumn(type = PrimaryKeyType.PARTITIONED)
    @CassandraType(type = CassandraType.Name.TIMEUUID, userTypeName = "assignment_id")
    private UUID assignmentId;
    @PrimaryKeyColumn(type = PrimaryKeyType.CLUSTERED)
    @CassandraType(type = CassandraType.Name.TIMEUUID, userTypeName = "submission_id")
    private UUID submissionId;
    @CassandraType(type = CassandraType.Name.TEXT, userTypeName = "username")
    private String username;
    @CassandraType(type = CassandraType.Name.TEXT)
    private String answer;
}
