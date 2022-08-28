import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Assessment } from '../models/assessment';
import { Observable } from 'rxjs';

@Injectable({providedIn: 'root'})
export class AssessmentsService {
    private localApi = "http://localhost:8080/assignment/";
    constructor(private httpClient: HttpClient) { }
    public getAssessmentsOfCoursesAssignments(couresId : string) : Observable<Assessment[]> {
        console.log(`Trying the local ${couresId}`);
        return this.httpClient.get<Assessment[]>(this.localApi + couresId + "/all");
    }
}