package io.horatius.farouk_university.models;


import io.horatius.farouk_university.dao.Scholar;
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
    private Scholar scholar;
    private String answer;

    public Submission(Builder builder){
        this.submissionId = builder.submissionId;
        this.assignment = builder.assignment;
        this.scholar = builder.scholar;
        this.answer = builder.answer;
    }
    public static class Builder {
        private UUID submissionId;
        private Assignment assignment;
        private Scholar scholar;
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

        public Builder user(Scholar scholar){
            this.scholar = scholar;
            return this;
        }

        public Builder user(String userId){
            this.scholar = new Scholar();
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
