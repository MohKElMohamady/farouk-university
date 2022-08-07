import { createAction, props } from "@ngrx/store";
import { Assignment } from "src/app/models/assignment";
import { Course } from "src/app/models/course";

export const fetchAvailableCourses = createAction("[Courses] Fetch available courses");

export const fetchUserCourses = createAction("[Courses] Fetch user courses");

export const successfullyFetchedAvailableCourses = createAction("[Courses] Successfully fetched available courses", props<{availableCourses : Course[]}>());

export const successfullyFetchedUserCourses = createAction("[Courses] Successfully fetched user courses", props<{userCourses : Course[]}>());

export const fetchAssignmentsOfCourse = createAction("[Courses] Fetch assignments of courses");

export const successfullyFetchedAssignmentsOfCourse = createAction("[Courses] Fetched assignments of courses", props<{assignments : Assignment[]}>());