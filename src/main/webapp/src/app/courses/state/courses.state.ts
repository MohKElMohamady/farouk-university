import { Assignment } from "src/app/models/assignment";
import { Course } from "src/app/models/course";

export interface CoursesState{
    availableCourses : Course[];
    userCourses : Course[];
    selectedCourse : Course;
}