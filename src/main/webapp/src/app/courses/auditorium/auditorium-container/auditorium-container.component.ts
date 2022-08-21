import { Component, OnInit } from '@angular/core';
import * as CourseSelectors from '../../state/courses.selectors';
import * as CourseActions from '../../state/courses.actions';
import * as UserActions from '../../../user/state/user.actions';
import { MatSnackBar, MatSnackBarModule } from '@angular/material/snack-bar';
import { ActivatedRoute, ActivatedRouteSnapshot, Route, Router } from '@angular/router';
import { Store } from '@ngrx/store';
import { map, Observable } from 'rxjs';
import { Assignment } from 'src/app/models/assignment';
import { Course } from 'src/app/models/course';


@Component({
  selector: 'app-auditorium-container',
  templateUrl: './auditorium-container.component.html',
  styleUrls: ['./auditorium-container.component.css']
})
export class AuditoriumContainerComponent implements OnInit {

  public course! : Observable<Course | undefined>;
  public assignments! : Observable<Assignment[] | undefined>

  constructor(private route : ActivatedRoute, private store : Store, private snackbar : MatSnackBar) { 
    this.route.params.pipe(
      map((params) => {
        console.log(params['courseId']);
        this.course = this.store.select(CourseSelectors.fetchCourseWithId({ courseId : params['courseId']}));
        this.store.dispatch(CourseActions.fetchAssignmentsOfCourse({courseId : params['courseId']}));
      })
    ).subscribe();
      this.assignments = this.store.select(CourseSelectors.fetchAssignments);
  }
  ngOnInit(): void {
  }

  public deleteCourse(course : Course | null | undefined) : void {
    // TODO: Use the user selector to see if the logged in user same as creator, but for now, use local storage
    if(localStorage.getItem("username") !== course?.courseKey.courseCreator){
      this.store.dispatch(CourseActions.deleteCourse({course : course}));
    } else {
      this.snackbar.open("You cannot delete this course because you are not the creator ", "Ok");
    }
  }
}
