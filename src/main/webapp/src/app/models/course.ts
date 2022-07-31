import { CourseKey } from "./course-key";
import { Enrollment } from "./enrollment";
import { User } from "./user";

export interface Course {
    courseKey : CourseKey;
    courseName : string;
    description : string;
    enrollmentKey : string;
    creator : User;
    enrollments : Enrollment[];
    capacity : number;
}