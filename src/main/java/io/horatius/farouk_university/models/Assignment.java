package io.horatius.farouk_university.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@AllArgsConstructor
@Data
@NoArgsConstructor
public class Assignment {
    private Course course;
    private UUID assignmentId;
    private String description;

    public Assignment(Builder builder){

    }

    public static class Builder {

        private Course course;
        private UUID assignmentId;
        private String description;
        public Builder(){

        }

        public Builder course(UUID courseId){
            this.course = new Course.Builder().courseId(courseId).build();
            return this;
        }

        public Builder assignmentId(UUID assignmentId){
            this.assignmentId = assignmentId;
            return this;
        }
        public Builder description(String description){
            this.description = description;
            return this;
        }
        public Assignment build(){
            return new Assignment(this);
        }
    }
}
