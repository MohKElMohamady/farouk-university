import { Component, EventEmitter, Input, OnInit, Output } from '@angular/core';
import { Course } from 'src/app/models/course';

@Component({
  selector: 'dashboard-display',
  templateUrl: './dashboard-display.component.html',
  styleUrls: ['./dashboard-display.component.css']
})
export class DashboardDisplayComponent implements OnInit {

  @Input()
  public availableCourses!: Course[] | null;
  @Input()
  public userCourses!: Course[] | null;
  @Output()
  public navigateToCourseEvent : EventEmitter<string> = new EventEmitter();
  @Output()
  public createNewCourseEvent : EventEmitter<void> = new EventEmitter();

  constructor() { 
  }

  ngOnInit(): void {
    
    
  }

  public navigateToCourse(courseId : string) {
    this.navigateToCourseEvent.emit(courseId);
  }

  public createNewCourse(){
    this.createNewCourseEvent.emit();
  }
}
