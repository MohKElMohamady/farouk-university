import { Course } from "./course";
import { User } from "./user";

export interface Enrollment{
    enrolledScholar : User;
    course : Course;
    enrollmentDate : string;
}