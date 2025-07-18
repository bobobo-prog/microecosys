import { CommonModule } from '@angular/common';
import { HttpClient,HttpClientModule } from '@angular/common/http';
import { Component } from '@angular/core';
import {FormBuilder, FormGroup, Validators,ReactiveFormsModule} from '@angular/forms';
import { Router } from '@angular/router';

@Component({
  selector: 'app-login',
  standalone: true,
  imports: [ReactiveFormsModule,
            CommonModule,
            HttpClientModule],
  templateUrl: './login.component.html',
  styleUrl: './login.component.scss'
  
})
export class LoginComponent {

  loginForm: FormGroup;
  errorMessage: string = '';

  constructor(
    private fb:FormBuilder,
    private http:HttpClient,
    private router:Router
  ){

      this.loginForm = this.fb.group({
        userName:['',Validators.required],
        password:['',Validators.required]
      })

  }


  login()
  {
    const formData = this.loginForm.value;

    this.http.post<{token:string}>('http://localhost:8080/auth/login',formData,{withCredentials:true})
    .subscribe({
      next:(response) => {
        localStorage.setItem('token',response.token);
        this.router.navigate(['/dashboard/users']);
      },
      error:()=>{
        this.errorMessage = 'Invalid credentials , please try again.'
      }
    })
  }




}
