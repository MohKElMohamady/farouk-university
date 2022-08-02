import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { AuthenticationService } from 'src/app/services/authentication.service';
import { Actions, createEffect, ofType } from '@ngrx/effects';
import * as userActions from './user.actions';
import { catchError, map, mergeMap, of } from 'rxjs';

@Injectable({providedIn: 'root'})
export class UserEffects {

    private loginEffect = createEffect(() => {
        return this.actions$.pipe(
            ofType(userActions.login),
            mergeMap((loginAction) => {
               return this.authenticationService.login({username: loginAction.username, password : loginAction.password}).pipe(
                map((user) => {
                    localStorage.setItem("username", user.username);
                    localStorage.setItem("password", user.password);
                    return userActions.loginSuccess({user});
                }),
                /*
                 * According to the documentation of
                 */
                catchError(() => {
                    return of(userActions.loginFailure())
                })
               )
            })
        );
    });

    

    private logoutEffect = createEffect

    constructor(private authenticationService : AuthenticationService, private actions$ : Actions) { }
    
}