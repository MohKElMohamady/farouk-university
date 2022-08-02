import { createReducer, on } from '@ngrx/store';
import { UserState } from './user.state';
import * as userActions from './user.actions';

export const initialState: UserState = {
    user: {
        username: '',
        password: '',
        firstName: '',
        lastName: '',
    },
    isLoggedIn: false,
};

export const userReducers = createReducer(
    initialState,
    on(userActions.loginSuccess, (state, action): UserState => {
        return {
            ...state,
            user: action.user,
            isLoggedIn: true,
        };
    }),
    on(userActions.logout, (state, action) : UserState => {
        return {
            ...state,
            user : {
                username : "",
                password : "",
                firstName : "",
                lastName : ""
            },
            isLoggedIn : false
        }
    }),
);
