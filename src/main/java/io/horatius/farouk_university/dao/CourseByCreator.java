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

@Table(value = "courses_by_user")
@AllArgsConstructor
@Data
@NoArgsConstructor
public class CourseByCreator {
    @PrimaryKeyColumn(value = "course_creator", type = PrimaryKeyType.PARTITIONED)
    private String courseCreator;
    @PrimaryKeyColumn(value = "course_id", type = PrimaryKeyType.PARTITIONED)
    @CassandraType(type = CassandraType.Name.TIMEUUID)
    private UUID courseId;
    @CassandraType(type = CassandraType.Name.TEXT, userTypeName = "course_name")
    private String courseName;
    @CassandraType(type = CassandraType.Name.TEXT)
    private String description;
    @CassandraType(type = CassandraType.Name.TEXT, userTypeName = "enrollment_key")
    private String enrollmentKey;
}
