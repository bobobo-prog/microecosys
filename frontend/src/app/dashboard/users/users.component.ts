import { CommonModule, NumberSymbol } from '@angular/common';
import { HttpClient } from '@angular/common/http';
import { Component } from '@angular/core';

interface User{


  id:number;
  username:string;
  email:string;
  role:string;
}




@Component({
  selector: 'app-users',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './users.component.html',
  styleUrl: './users.component.scss'
})
export class UsersComponent {


  users: User[] = [];
  dataFetched = false;

  constructor(private http:HttpClient)
  {

  }


  fetchUsers():void{
    const token = localStorage.getItem('token');

    if(!token)
    {
      console.error('JWT not found');
    }

    const headers = {
      'Authorization':`Bearer ${token}`
    }

    this.http.get<User[]>('http://localhost:8080/user/getAllUsers',{headers})
      .subscribe({
        next:(data) =>{ 
          this.users = data;
          this.dataFetched = true;
        },
        
        error:(err) => {
          console.error('Error while fetching data!',err);
          this.users = [];
          this.dataFetched = true;
        }

      });


  }




}
