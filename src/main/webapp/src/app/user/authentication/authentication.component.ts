import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';

@Component({
  selector: 'app-authentication',
  templateUrl: './authentication.component.html',
  styleUrls: ['./authentication.component.css']
})
export class AuthenticationComponent implements OnInit {

  loginForm! : FormGroup;

  constructor(private formBuilder : FormBuilder) { 
    this.loginForm = this.formBuilder.group({
      username : [,[Validators.required]],
      password : [[], [Validators.required]],
      rememberMe : []
    });
  }

  ngOnInit(): void {
  }

}
