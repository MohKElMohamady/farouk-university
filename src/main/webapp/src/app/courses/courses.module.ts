import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { DashboardContainerComponent } from '../dashboard-container/dashboard-container.component';
import { DashboardDisplayComponent } from '../dashboard-display/dashboard-display.component';
import { EffectsModule } from '@ngrx/effects';
import { CoursesEffect } from './state/courses.effects';
import { StoreModule } from '@ngrx/store';
import { coursesReducers } from './state/courses.reducers';
import { HttpClientModule } from '@angular/common/http';
import { MaterialModule } from '../material.module';



@NgModule({
  declarations: [
    DashboardContainerComponent,
    DashboardDisplayComponent
  ],
  imports: [
    CommonModule,
    StoreModule.forFeature("Courses", coursesReducers),
    EffectsModule.forFeature([CoursesEffect]),
    HttpClientModule,
    MaterialModule
  ],
  exports : [
    DashboardContainerComponent,
  ]
})
export class CoursesModule { }
