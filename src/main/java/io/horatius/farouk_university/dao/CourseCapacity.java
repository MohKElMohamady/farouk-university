package io.horatius.farouk_university.dao;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.cassandra.core.mapping.CassandraType;
import org.springframework.data.cassandra.core.mapping.PrimaryKey;
import org.springframework.data.cassandra.core.mapping.Table;

import java.util.UUID;

@Table(value = "course_free_slots")
@AllArgsConstructor
@Data
@NoArgsConstructor
public class CourseCapacity {
    @CassandraType(type = CassandraType.Name.TIMEUUID)
    @PrimaryKey(value = "course_id")
    private UUID courseId;
    @CassandraType(type = CassandraType.Name.COUNTER)
    private int capacity;
}
