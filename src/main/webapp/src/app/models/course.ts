import { Enrollment } from "./enrollment";
import { User } from "./user";

export interface Course {
    courseId : string;
    courseName : string;
    description : string;
    enrollmentKey : string;
    creator : User;
    enrollments : Enrollment[];
    capacity : number;
}