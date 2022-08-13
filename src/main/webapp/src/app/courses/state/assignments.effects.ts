import { Injectable } from '@angular/core';
import { Actions, createEffect, ofType } from '@ngrx/effects';
import { map, mergeMap } from 'rxjs';
import { AssignmentsService } from 'src/app/services/assignments.service';
import * as AssignmentActions from '../state/courses.actions';
@Injectable({ providedIn: 'root' })
export class AssignmentsEffects {
    constructor(
        private actions$: Actions,
        private assignmentsService: AssignmentsService
    ) { }

    fetchAssignmentsOfCourse$ = createEffect(() => {
        return this.actions$.pipe(
            ofType(AssignmentActions.fetchAssignmentsOfCourse),
            mergeMap((action) =>
                this.assignmentsService
                    .getAssignmentsOfCourse(action.courseId)
                    .pipe(
                        map((data) =>
                            AssignmentActions.successfullyFetchedAssignmentsOfCourse({
                                assignments: data,
                            })
                        )
                    )
            )
        );
    });
}
