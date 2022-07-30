package io.horatius.farouk_university.models;

import io.horatius.farouk_university.dao.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@AllArgsConstructor
@Data
@NoArgsConstructor
public class Assessment {
    private UUID assessmentId;
    private Submission submission;
    private User user;
    private int grade;
    private String comment;


    public Assessment(Builder builder){
        this.assessmentId = builder.assignmentId;
        this.submission = builder.submission;
        this.grade = builder.grade;
        this.comment = builder.comment;
    }

    public static class Builder {

        private UUID assignmentId;
        private Submission submission;
        private String comment;
        private int grade;
        public Builder(){

        }

        public Builder submission(UUID submissionId){
            this.submission = new Submission.Builder().submissionId(submissionId).build();
            return this;
        }

        public Builder assignmentId(UUID assignmentId){
            this.assignmentId = assignmentId;
            return this;
        }
        public Builder comment(String comment){
            this.comment = comment;
            return this;
        }
        public Builder grade(int grade){
            this.grade = grade;
            return this;
        }
        public Assessment build(){
            return new Assessment(this);
        }
    }
}
