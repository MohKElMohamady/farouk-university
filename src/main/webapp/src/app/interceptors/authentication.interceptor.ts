import { Injectable } from '@angular/core';
import {
  HttpEvent,
  HttpInterceptor,
  HttpHandler,
  HttpRequest,
} from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable()
export class BasicAuthHeader implements HttpInterceptor {
  constructor() {}

  intercept(
    req: HttpRequest<any>,
    next: HttpHandler
  ): Observable<HttpEvent<any>> {
    /*
     * Placeholder to retrieve username and password from local storage after implementing login
     */
    const username = localStorage.getItem("username");
    const password = localStorage.getItem("password");
    const authReq = req.clone({
      setHeaders: {
        'Content-Type': 'application/json',
        Authorization: 'Basic ' + btoa('steve.eisman:MistookLeverageForGenius'),
      },
    });
    return next.handle(authReq);
  }
}
