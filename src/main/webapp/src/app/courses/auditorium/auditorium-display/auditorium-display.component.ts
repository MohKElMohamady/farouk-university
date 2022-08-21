import { Component, EventEmitter, Input, OnInit, Output } from '@angular/core';
import { Assignment } from 'src/app/models/assignment';
import { Course } from 'src/app/models/course';

@Component({
  selector: 'app-auditorium-display',
  templateUrl: './auditorium-display.component.html',
  styleUrls: ['./auditorium-display.component.css']
})
export class AuditoriumDisplayComponent implements OnInit {

  @Input()
  public course! : Course | undefined | null;
  @Input()
  public assignments! : Assignment[] | undefined | null;
  @Output()
  public deleteCourseEvent : EventEmitter<Course | null | undefined> = new EventEmitter<Course | null | undefined>();

  constructor() { 
    
  }

  ngOnInit(): void {
  }

  deleteCourse(course : Course | null | undefined) : void {
    this.deleteCourseEvent.emit(course);
  }
}
