import { Assignment } from "./assignment";
import { User } from "./user";

export interface Submission {
    submissionId : string;
    assignment : Assignment;
    scholar : User;
    answer : String;
}