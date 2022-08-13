import { Injectable } from '@angular/core';
import { MatSnackBar, MatSnackBarModule } from '@angular/material/snack-bar';
import { Actions, createEffect, ofType } from '@ngrx/effects';
import { map, mergeMap } from 'rxjs';
import { Course } from 'src/app/models/course';
import { AssignmentsService } from 'src/app/services/assignments.service';
import { CoursesService } from 'src/app/services/courses.service';
import * as coursesActions from './courses.actions';

@Injectable({providedIn: 'root'})
export class CoursesEffect {
    

    public fetchAvailableCourses = createEffect(() => {
        return this.actions$.pipe(
            ofType(coursesActions.fetchAvailableCourses),
            mergeMap(() => {
                return this.coursesService.availableCourses().pipe(
                    map((courses : Course[]) => {
                        return coursesActions.successfullyFetchedAvailableCourses({availableCourses : courses});
                    })
                );
            }),
        );
    });

    public fetchUserCourses = createEffect(() => {
        return this.actions$.pipe(
            ofType(coursesActions.fetchUserCourses),
            mergeMap(() => {
                return this.coursesService.userCourses().pipe(
                    map((courses : Course[]) => {
                        this.snackbar.open('Successfully fetched user courses', 'Ok', {duration : 4000});
                        return coursesActions.successfullyFetchedUserCourses({userCourses : courses});
                    })
                );
            }),
        );
    });

    constructor(private coursesService : CoursesService, private actions$ : Actions, private snackbar : MatSnackBar, private assignmentsService : AssignmentsService) { 

    }

}