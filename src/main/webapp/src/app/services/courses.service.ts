import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Course } from '../models/course';

@Injectable({providedIn: 'root'})
export class ServiceNameService {

    private localApi = "http://localhost:8080/course";

    constructor(private httpClient: HttpClient) { }
    
    public availableCourses() : Observable<Course[]> {
        return this.httpClient.get<Course[]>(this.localApi + "/available");
    }

    public userCourses() : Observable<Course[]> {
        return this.httpClient.get<Course[]>(this.localApi + "available-courses");
    }

    public createCourse(course : Course) : Observable<Course> {
        return this.httpClient.post<Course>(this.localApi, course);
    }
}