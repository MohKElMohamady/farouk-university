import { Component, OnInit } from '@angular/core';
import { Store } from '@ngrx/store';
import { map, Observable } from 'rxjs';
import { Course } from '../../../models/course';
import * as coursesActions from '../../state/courses.actions'
import * as coursesSelectors from '../../state/courses.selectors'
import { CoursesService } from '../../../services/courses.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-dashboard-container',
  templateUrl: './dashboard-container.component.html',
  styleUrls: ['./dashboard-container.component.css']
})
export class DashboardContainerComponent implements OnInit {

  public availableCourses$ : Observable<Course[] >;
  public userCourses$ : Observable<Course[]>;
  constructor(private store : Store, private router : Router ,private coursesService : CoursesService) {
    this.store.dispatch(coursesActions.fetchAvailableCourses());
    this.store.dispatch(coursesActions.fetchUserCourses());
    this.availableCourses$ = this.store.select(coursesSelectors.availableCourses);
    this.userCourses$ = this.store.select(coursesSelectors.fetchUserCourse);
  }

  ngOnInit(): void {

  }

  public navigateToCourse(courseId : string){
    this.router.navigate([courseId]);
  }

  public createNewCourse(){
    this.router.navigate(['create']);
  }
}
