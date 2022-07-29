package io.horatius.farouk_university.models;

import io.horatius.farouk_university.dao.CourseByCreatorAndId;
import io.horatius.farouk_university.dao.User;
import lombok.*;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
@AllArgsConstructor
@Data
@NoArgsConstructor
public class Course {
    private UUID courseId;
    private String courseName;
    private String description;
    private String enrollmentKey;
    private User creator;
    private List<Enrollment> enrollmentList;
    private int capacity;

    public Course(CourseByCreatorAndId courseByCreatorAndId, int capacity){
        this.courseId = courseByCreatorAndId.getCourseKey().getCourseId();
        this.description = courseByCreatorAndId.getDescription();
        this.enrollmentKey = courseByCreatorAndId.getEnrollmentKey();
        /* Inject the user with the CourseByUser later as a parameter in the constructor */
        this.enrollmentList = new ArrayList<>();
        this.capacity = capacity;
    }

    public Course(Builder builder){
        this.courseId = builder.courseId;
        this.description = builder.description;
        this.enrollmentList = builder.enrollmentList;
        this.capacity = builder.capacity;
    }

    public static class Builder {
        private UUID courseId;
        private String description;
        private User creator;
        private List<Enrollment> enrollmentList = new ArrayList<>();
        private int capacity;
        public Builder(){}

        public Builder courseId(UUID courseId){
            this.courseId = courseId;
            return this;
        }
        public Builder description(String description){
            this.description = description;
            return this;
        }
        public Builder creator(User creator){
            this.creator = creator;
            return this;
        }
        public Builder enrollmentList(List<Enrollment> enrollments){
            this.enrollmentList = List.copyOf(enrollments);
            return this;
        }
        public Builder capacity(int capacity){
            this.capacity = capacity;
            return this;
        }

        public Course build(){
            return new Course(this);
        }

    }
}
