package io.horatius.farouk_university.models;

import com.datastax.oss.driver.api.core.uuid.Uuids;
import io.horatius.farouk_university.dao.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;
@AllArgsConstructor
@Data
@NoArgsConstructor
public class Enrollment {
    private User enrolledScholar;
    private Course course;
    private UUID enrollmentDate;

    public Enrollment(Builder builder){
        this.enrolledScholar = builder.enrolledScholar;
        this.course = builder.course;
        this.enrollmentDate = builder.enrollmentDate;
    }

    public static class Builder {
        private User enrolledScholar = new User();
        private Course course = new Course();
        private UUID enrollmentDate;

        public Builder enrolledUser(User scholar){
            this.enrolledScholar = scholar;
            return this;
        }

        public Builder enrolledUser(String username){
            this.enrolledScholar = new User(username);
            return this;
        }
        public Builder course(UUID courseId){
            this.course = new Course
                    .Builder()
                    .courseId(courseId)
                    .build();
            return this;
        }
        public Builder course(Course course){
            this.course = course;
            return this;
        }

        public Builder enrollNow(){
            this.enrollmentDate = Uuids.timeBased();
            return this;
        }

        public Enrollment build(){
            return new Enrollment(this);
        }
    }
}
