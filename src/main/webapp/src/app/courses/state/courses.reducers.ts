import { createReducer, on } from '@ngrx/store';
import { CoursesState } from './courses.state';
import * as CoursesActions from './courses.actions';

export const initialState: CoursesState = {
    availableCourses: [],
    userCourses: [],
    selectedCourse: {
        courseKey: {
            courseId : '555',
            courseCreator : 'Jeremy Strong'
        },
        courseName: 'Introduction to Economics',
        creator: {
            username : "",
            password : ""
        },
        capacity: 30,
        description: 'They mistook leverage for genius',
        enrollmentKey: 'eisman',
        enrollments: [],
    },
};

export const coursesReducers = createReducer(
    initialState,
    on(
        CoursesActions.successfullyFetchedAvailableCourses,
        (state, action): CoursesState => {
            return {
                ...initialState,
                availableCourses: action.availableCourses,
            };
        }
    ),
    on(CoursesActions.successfullyFetchedUserCourses,
        (state, aciton) : CoursesState => {
            return {
                ...initialState,
                userCourses : aciton.userCourses
            }
        }),
    on(CoursesActions.successfullyFetchedAssignmentsOfCourse, 
        (state,action) : CoursesState => {
            let course = JSON.parse(JSON.stringify(state.selectedCourse));
            console.log(action.assignments);
            course.assignments = action.assignments;
            return {
                ...initialState,
                selectedCourse : course
            }
        })
);
