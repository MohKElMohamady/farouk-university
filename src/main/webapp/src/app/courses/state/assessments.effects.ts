import { Injectable } from '@angular/core';
import { Actions, createEffect, ofType } from '@ngrx/effects';
import { catchError, map, of, switchMap } from 'rxjs';
import { AssessmentsService } from 'src/app/services/assessments.service';
import * as CoursesActions from '../state/courses.actions';
@Injectable({providedIn: 'root'})
export class AssessmentsEffects {
    private fetchAssessmentsOfCoursesAssignments = createEffect(() => {
        return this.actions$.pipe(
            ofType(CoursesActions.fetchLatestAssessmentsOfAssignments),
            switchMap((action) => {
                return this.assessmentsService.getAssessmentsOfCoursesAssignments(action.courseId).pipe(
                    map(assessments => {
                       return (CoursesActions.fetchingLatestAssessmentsSuccess({assessments : assessments})); 
                    }),
                    catchError((err) => {
                        return of(CoursesActions.fetchingLatestAssessmentFailure());
                    })
                );
            })
        );
    });
    constructor(private actions$ : Actions, private assessmentsService : AssessmentsService) { }
}