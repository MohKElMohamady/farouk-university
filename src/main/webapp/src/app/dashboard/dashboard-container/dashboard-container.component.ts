import { Component, OnInit } from '@angular/core';
import { Store } from '@ngrx/store';
import { map, Observable } from 'rxjs';
import { Course } from '../../models/course';
import * as coursesActions from '../../courses/state/courses.actions'
import * as coursesSelectors from '../../courses/state/courses.selectors'
import { CoursesService } from '../../services/courses.service';

@Component({
  selector: 'app-dashboard-container',
  templateUrl: './dashboard-container.component.html',
  styleUrls: ['./dashboard-container.component.css']
})
export class DashboardContainerComponent implements OnInit {

  public availableCourses$ : Observable<Course[] >;
  public userCourses$ : Observable<Course[]>;
  constructor(private store : Store, private coursesService : CoursesService) {
    this.store.dispatch(coursesActions.fetchAvailableCourses());
    this.store.dispatch(coursesActions.fetchUserCourses());
    this.availableCourses$ = this.store.select(coursesSelectors.availableCourses);
    this.userCourses$ = this.store.select(coursesSelectors.fetchUserCourse);
    this.coursesService.userCourses().pipe(
      map((courses) => {
        console.log(courses);
      })
    ).subscribe();
  }

  ngOnInit(): void {
    
  }

}
