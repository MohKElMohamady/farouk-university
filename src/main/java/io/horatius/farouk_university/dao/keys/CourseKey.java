package io.horatius.farouk_university.dao.keys;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.cassandra.core.cql.PrimaryKeyType;
import org.springframework.data.cassandra.core.mapping.CassandraType;
import org.springframework.data.cassandra.core.mapping.PrimaryKeyClass;
import org.springframework.data.cassandra.core.mapping.PrimaryKeyColumn;

import java.util.UUID;
@AllArgsConstructor
@Data
@NoArgsConstructor
@PrimaryKeyClass
public class CourseKey {
    @PrimaryKeyColumn(name = "course_creator", ordinal = 0, type = PrimaryKeyType.PARTITIONED)
    @CassandraType(type = CassandraType.Name.TEXT)
    private String courseCreator;
    @PrimaryKeyColumn(name = "course_id", ordinal = 1, type = PrimaryKeyType.PARTITIONED)
    @CassandraType(type = CassandraType.Name.TIMEUUID)
    private UUID courseId;
}
