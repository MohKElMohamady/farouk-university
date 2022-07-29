package io.horatius.farouk_university.dao;

import io.horatius.farouk_university.dao.keys.CourseKey;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.cassandra.core.mapping.CassandraType;
import org.springframework.data.cassandra.core.mapping.PrimaryKey;
import org.springframework.data.cassandra.core.mapping.Table;

@Table(value = "courses_by_creator_and_id")
@AllArgsConstructor
@Data
@NoArgsConstructor
public class CourseByCreatorAndId {
    @PrimaryKey(value = "course_key")
    private CourseKey courseKey;
    @CassandraType(type = CassandraType.Name.TEXT, userTypeName = "course_name")
    private String courseName;
    @CassandraType(type = CassandraType.Name.TEXT)
    private String description;
    @CassandraType(type = CassandraType.Name.TEXT, userTypeName = "enrollment_key")
    private String enrollmentKey;
}
