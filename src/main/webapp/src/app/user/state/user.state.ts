import { User } from "src/app/models/user";

export interface UserState{
    user : User;
    isLoggedIn : boolean;
}