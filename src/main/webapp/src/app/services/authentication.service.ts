import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { User } from '../models/user';
import { Observable } from 'rxjs';

@Injectable({providedIn: 'root'})
export class AuthenticationService {
    constructor(private httpClient: HttpClient) { }
    
    private api = "http://localhost:8080/user";

    public login(user : User) : Observable<User> {
        return this.httpClient.post<User>(this.api + "/login", user);
    }

    public logout() : Observable<User> {
        return this.httpClient.post<User>(this.api + "/logout", {});
    }
    
}