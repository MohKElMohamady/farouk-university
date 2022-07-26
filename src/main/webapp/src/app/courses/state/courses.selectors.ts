import { createFeatureSelector, createSelector, MemoizedSelector, Selector } from "@ngrx/store";
import { Assessment } from "src/app/models/assessment";
import { Assignment } from "src/app/models/assignment";
import { Course } from "src/app/models/course";
import { CoursesState } from "./courses.state";

export const coursesFeatureState = createFeatureSelector<CoursesState>("Courses");


export const availableCourses = createSelector(coursesFeatureState, (state : CoursesState) : Course[] => {
    return state.availableCourses;
});

export const fetchUserCourse = createSelector(coursesFeatureState, (state : CoursesState) : Course[] => {
    return state.userCourses;
});

/*
 * Courtesy of:
* https://github.com/ngrx/platform/issues/2980
 * How to provide a selector a parameter to be used in the selector
 * Why use a factory function? Extract of the link above:
 * One of the big benefits of using selectors is that a selector is memoized, but with selectors with props it's very easy to "break" this. 
 * Most of the problems/questions I see is because selector with props aren't used correctly, resulting in selectors that are executed multiple times.
 */

export const fetchCourseWithId = (props : {courseId : string}) => { return createSelector(coursesFeatureState, (state : CoursesState) : Course | undefined => {
    const courseId = JSON.parse(JSON.stringify(props.courseId));
    const course = state.userCourses.find(course => course.courseKey.courseId === courseId);
    return course;
});
}

export const fetchAssignments = createSelector(coursesFeatureState, (state : CoursesState) : Assignment[] | undefined => {
    return state.selectedCourse?.assignments;
});

export const latestAssessmentsOfCourse = createSelector(coursesFeatureState, (state : CoursesState) : Assessment[] | undefined => {
    return state.latestAssessmentsOfCoursesAssignment
})