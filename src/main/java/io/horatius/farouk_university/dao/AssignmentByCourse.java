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
@Table(value = "assignments_by_course")
public class AssignmentByCourse {
    @PrimaryKeyColumn(value = "course_id", type = PrimaryKeyType.PARTITIONED)
    @CassandraType(type = CassandraType.Name.TIMEUUID)
    private UUID courseId;
    @PrimaryKeyColumn(type = PrimaryKeyType.CLUSTERED)
    @CassandraType(type = CassandraType.Name.TIMEUUID, userTypeName = "assignment_id")
    private UUID assignmentId;
    @CassandraType(type = CassandraType.Name.TEXT, userTypeName = "creator")
    private String creator;
    @CassandraType(type = CassandraType.Name.TEXT, userTypeName = "description")
    private String description;

}
