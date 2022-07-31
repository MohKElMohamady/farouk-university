import { Submission } from "./submission";

export interface Assessment {
    assessmentId : string;
    submission : Submission;
    grade : number;
    comment : String;
}