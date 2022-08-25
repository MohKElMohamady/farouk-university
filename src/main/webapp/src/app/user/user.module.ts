import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { EffectsModule } from '@ngrx/effects';
import { userReducers } from './state/user.reducers';
import { StoreModule } from '@ngrx/store';
import {UserEffects} from './state/user.effects';
import { AuthenticationComponent } from './authentication/authentication.component';
import { MaterialModule } from '../material.module';
import { ReactiveFormsModule } from '@angular/forms';
import { RegistrationComponent } from './registration/registration.component';
import { RouterModule } from '@angular/router';
import { SharedModule } from '../shared.module';


@NgModule({
  declarations: [
    AuthenticationComponent,
    RegistrationComponent
  ],
  imports: [
    CommonModule,
    SharedModule,
    MaterialModule,
    ReactiveFormsModule,
    EffectsModule.forFeature([UserEffects]),
    StoreModule.forFeature("User", userReducers),
    RouterModule.forChild([
      {path : "login", component : AuthenticationComponent},
      {path: "register", component : RegistrationComponent}
    ])
  ]
})
export class UserModule { }
