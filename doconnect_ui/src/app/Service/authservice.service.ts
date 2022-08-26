import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class AuthserviceService {

  constructor(private http:HttpClient) { }

  login(username:string,password:string)
  {
    let loginRequest = {
      username:username,
      password:password
    }
    const headers = new HttpHeaders({Authorization:'Basic ' + btoa(username+ ":" +password)});
    return this.http.post("http://localhost:8080/login",loginRequest,{headers,responseType :'json'});
  }

  register(regObj:any)
  {
    return this.http.post("http://localhost:8080/user/register",regObj,{responseType:'text'});
  }

  getAllUsers(username:any,password:any)
  {
    const headers = new HttpHeaders({Authorization:'Basic ' + btoa(username+ ":" +password)}); 
    return this.http.get("http://localhost:8080/user/get",{headers,responseType :'json'});
  }
}

