import { Course } from "./course";

export interface Assignment {
    assignmentId : string;
    course : Course;
    description : string;
}