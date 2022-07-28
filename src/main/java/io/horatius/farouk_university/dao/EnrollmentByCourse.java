package io.horatius.farouk_university.dao;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.cassandra.core.cql.PrimaryKeyType;
import org.springframework.data.cassandra.core.mapping.CassandraType;
import org.springframework.data.cassandra.core.mapping.PrimaryKeyColumn;
import org.springframework.data.cassandra.core.mapping.Table;

import java.util.UUID;

@Table(value = "enrollments_by_user")
@AllArgsConstructor
@Data
@NoArgsConstructor
public class EnrollmentByCourse {
    @PrimaryKeyColumn(value = "course_id",type = PrimaryKeyType.PARTITIONED)
    private UUID courseId;
    @CassandraType(type = CassandraType.Name.TEXT, userTypeName = "user_id")
    private String userId;
    @PrimaryKeyColumn(type = PrimaryKeyType.CLUSTERED, value = "enrollment_date")
    @CassandraType(type = CassandraType.Name.TIMEUUID)
    private UUID enrollmentDate;

}
