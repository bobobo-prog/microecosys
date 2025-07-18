import { Injectable } from '@angular/core';
const TOKEN_KEY = "token";

@Injectable({
  providedIn: 'root'
})
export class TokenService {

  

  constructor() { }

  public saveToken(token:string):void{

    localStorage.setItem(TOKEN_KEY,token);
  }

  public getToken():string|null{
    return localStorage.getItem(TOKEN_KEY);
  }


  public clearToken():void{
    localStorage.removeItem(TOKEN_KEY);
  }


  public decodeToken():any|null{
    const token = this.getToken();

    if(!token) return null;

    try{

      const payload = token.split('.')[1];
      const decoded = atob(payload);
      return JSON.parse(decoded);

    }
    catch(e)
    {
      console.error(e);
      return null;
    }

  }


  public isTokenExpired():boolean{
    const decoded = this.decodeToken();
    if(!decoded || !decoded.exp) return true;

    const currTime = Math.floor(Date.now()/1000);
    return decoded.exp<currTime;


  }












}
