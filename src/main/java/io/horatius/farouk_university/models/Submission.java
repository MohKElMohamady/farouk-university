package io.horatius.farouk_university.models;


import io.horatius.farouk_university.dao.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@AllArgsConstructor
@Data
@NoArgsConstructor
public class Submission {
    private UUID submissionId;
    private Assignment assignment;
    private Assessment assessment;
    private User scholar;
    private String answer;

    public Submission(Builder builder){
        this.submissionId = builder.submissionId;
        this.assignment = builder.assignment;
        this.assessment = builder.assessment;
        this.scholar = builder.scholar;
        this.answer = builder.answer;
    }
    public static class Builder {
        private UUID submissionId;
        private Assignment assignment;
        private Assessment assessment;
        private User scholar;
        private String answer;

        public Builder submissionId(UUID submissionId){
            this.submissionId = submissionId;
            return this;
        }

        public Builder assignment(Assignment assignment){
            this.assignment = assignment;
            return this;
        }

        public Builder assignment(UUID assignmentId){
            this.assignment = new Assignment.Builder().assignmentId(assignmentId).build();
            return this;
        }

        private Builder assessment(Assessment assessment){
            this.assessment = assessment;
            return this;
        }

        public Builder user(User scholar){
            this.scholar = scholar;
            return this;
        }

        public Builder user(String userId){
            this.scholar = new User();
            this.scholar.setUsername(userId);
            return this;
        }

        public Builder answer(String answer){
            this.answer = answer;
            return this;
        }

        public Submission build(){
            return new Submission(this);
        }
    }
}
