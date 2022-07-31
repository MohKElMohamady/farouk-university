import { createReducer, on } from '@ngrx/store';
import { CoursesState } from './courses.state';
import * as coursesActions from './courses.actions';

export const initialState: CoursesState = {
    availableCourses: [],
    userCourses: [],
    selectedCourse: {
        courseKey: {
            courseId : '555',
            courseCreator : 'Jeremy Strong'
        },
        courseName: 'Introduction to Economics',
        creator: {},
        capacity: 30,
        description: 'They mistook leverage for genius',
        enrollmentKey: 'eisman',
        enrollments: [],
    },
    assignments: [],
};

export const coursesReducers = createReducer(
    initialState,
    on(
        coursesActions.successfullyFetchedAvailableCourses,
        (state, action): CoursesState => {
            return {
                ...initialState,
                availableCourses: action.availableCourses,
            };
        }
    ),
    on(coursesActions.successfullyFetchedUserCourses,
        (state, aciton) : CoursesState => {
            return {
                ...initialState,
                userCourses : aciton.userCourses
            }
        }),
    on(coursesActions.successfullyFetchedAssignmentsOfCourse, 
        (state,action) : CoursesState => {
            return {
                ...initialState,
                assignments : action.assignments
            }
        })
);
