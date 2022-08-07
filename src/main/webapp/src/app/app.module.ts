import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { StoreModule } from '@ngrx/store';
import { EffectsModule } from '@ngrx/effects';
import { MaterialModule } from './material.module';
import { HomeComponent } from './home/home.component';
import { CoursesModule } from './courses/courses.module';
import { StoreDevtoolsModule } from '@ngrx/store-devtools';
import { environment } from '../environments/environment';
import { HTTP_INTERCEPTORS } from '@angular/common/http';
import { BasicAuthHeader } from './interceptors/authentication.interceptor';

@NgModule({
  declarations: [
    AppComponent,
    HomeComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    BrowserAnimationsModule,
    StoreModule.forRoot({}),
    EffectsModule.forRoot([]),
    StoreDevtoolsModule.instrument(),
    MaterialModule,
    CoursesModule
  ],
  providers: [{provide: HTTP_INTERCEPTORS, useClass: BasicAuthHeader, multi : true}],
  bootstrap: [AppComponent]
})
export class AppModule { }
