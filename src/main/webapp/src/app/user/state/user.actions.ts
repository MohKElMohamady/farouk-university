import { createAction, props } from "@ngrx/store";
import { User } from "src/app/models/user";

export const login = createAction("[User] Login Attempt", props<{username : string, password : string}>());

export const loginSuccess = createAction("[User] Login success", props<{user : User}>());

export const loginFailure = createAction("[User] Login failure");

export const logout = createAction("[User] Logout");

export const registration = createAction("[User] Registration");

export const registrationSuccess = createAction("[User] Registration success", props<{user : User}>);

export const registrationFailure = createAction("[User] Registration failure");
