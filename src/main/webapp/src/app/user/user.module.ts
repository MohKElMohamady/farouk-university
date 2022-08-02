import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { EffectsModule } from '@ngrx/effects';
import { userReducers } from './state/user.reducers';
import { StoreModule } from '@ngrx/store';
import {UserEffects} from './state/user.effects';


@NgModule({
  declarations: [],
  imports: [
    CommonModule,
    EffectsModule.forFeature([UserEffects]),
    StoreModule.forFeature("User", userReducers)
  ]
})
export class UserModule { }
