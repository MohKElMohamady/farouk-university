import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Assignment } from '../models/assignment';
import { Observable } from 'rxjs';

@Injectable({providedIn: 'root'})
export class AssignmentsService {

    private api = "http://localhost:8080/assignment/";

    constructor(private httpClient: HttpClient) { }
    

    public getAssignmentsOfCourse(courseId : string) : Observable<Assignment[]> {
        return this.httpClient.get<Assignment[]>(this.api + courseId)
    }
}