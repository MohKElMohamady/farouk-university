import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { DashboardContainerComponent } from './dashboard/dashboard-container/dashboard-container.component';
import { DashboardDisplayComponent } from './dashboard/dashboard-display/dashboard-display.component';
import { EffectsModule } from '@ngrx/effects';
import { CoursesEffect } from './state/courses.effects';
import { StoreModule } from '@ngrx/store';
import { coursesReducers } from './state/courses.reducers';
import { HttpClientModule } from '@angular/common/http';
import { MaterialModule } from '../material.module';
import { CreateCourseComponent } from './create-course/create-course.component';

import { AuditoriumContainerComponent } from './auditorium/auditorium-container/auditorium-container.component';
import { AuditoriumDisplayComponent } from './auditorium/auditorium-display/auditorium-display.component';
import { RouterModule } from '@angular/router';



@NgModule({
  declarations: [
    DashboardContainerComponent,
    DashboardDisplayComponent,
    CreateCourseComponent,
    AuditoriumContainerComponent,
    AuditoriumDisplayComponent
  ],
  imports: [
    CommonModule,
    StoreModule.forFeature("Courses", coursesReducers),
    EffectsModule.forFeature([CoursesEffect]),
    HttpClientModule,
    MaterialModule,
    RouterModule.forChild([
      
      {path : "dashboard", component : DashboardContainerComponent},
      /*
       * TODO: Why is when create course component placed under the courseId, it does not get navigated to it
       * Maybe the idea is: place all the components that require path parameters at the end
       */
      {path : "create", component : CreateCourseComponent},
      {path : ":courseId" , component : AuditoriumContainerComponent,},
      
    ])
  ],
  exports : [
    DashboardContainerComponent,
  ]
})
export class CoursesModule { }
