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
    private User enrolledUser;
    private Course course;
    private UUID enrollmentDate;

    public Enrollment(Builder builder){
        this.enrolledUser = builder.enrolledUser;
        this.course = builder.course;
        this.enrollmentDate = builder.enrollmentDate;
    }

    public static class Builder {
        private User enrolledUser = new User();
        private Course course = new Course();
        private UUID enrollmentDate;

        public Builder enrolledUser(User user){
            this.enrolledUser = user;
            return this;
        }

        public Builder enrolledUser(String username){
            this.enrolledUser = new User(username);
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
