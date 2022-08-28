import { createAction, props } from "@ngrx/store";
import { Assessment } from "src/app/models/assessment";
import { Assignment } from "src/app/models/assignment";
import { Course } from "src/app/models/course";

export const fetchAvailableCourses = createAction("[Courses] Fetch available courses");

export const fetchUserCourses = createAction("[Courses] Fetch user courses");

export const selectCourse = createAction("[Courses] Fetch the course to display", props<{courseId : string}>());

export const successfullyFetchedAvailableCourses = createAction("[Courses] Successfully fetched available courses", props<{availableCourses : Course[]}>());

export const successfullyFetchedUserCourses = createAction("[Courses] Successfully fetched user courses", props<{userCourses : Course[]}>());

export const fetchAssignmentsOfCourse = createAction("[Courses] Fetch assignments of courses", props<{courseId : string}>());

export const successfullyFetchedAssignmentsOfCourse = createAction("[Courses] Fetched assignments of courses", props<{assignments : Assignment[]}>());

export const deleteCourse = createAction("[Courses] Delete course", props<{course : Course | null | undefined}>());

export const successfullyDeleteCourse = createAction("[Courses] Successfully delete course");

export const failedToDeleteCourse = createAction("[Courses] Failed to delete course");

export const fetchLatestAssessmentsOfAssignments = createAction("[Courses] Fetching latest assessments of all assignments if present", props<{courseId : string}>());

export const fetchingLatestAssessmentsSuccess = createAction("[Courses] Successfully fetched the assignments", props<{assessments : Assessment[]}>());

export const fetchingLatestAssessmentFailure = createAction("[Courses] Successfully fetched the assessments of the courses' assignments");
