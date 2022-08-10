import { Component, Input, OnInit } from '@angular/core';
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

  constructor() { 
  
  }

  ngOnInit(): void {
  }

}
