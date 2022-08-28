import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root',
})
export class AuthserviceService {
  constructor(private http: HttpClient) {}

  login(username: string, password: string) {
    let loginRequest = {
      username: username,
      password: password,
    };
    // const headers = new HttpHeaders({
    //   Authorization: 'Basic ' + btoa(username + ':' + password),
    // });
    return this.http.post('http://localhost:8081/doconnect/login', loginRequest, {
      responseType: 'json',
    });
  }

  register(regObj: any) {
    return this.http.post('http://localhost:8081/doconnect/user/register', regObj, {
      responseType: 'text',
    });
  }

  getAllUsers(username: any, password: any) {
    const headers = new HttpHeaders({
      Authorization: 'Basic ' + btoa(username + ':' + password),
    });
    return this.http.get('http://localhost:8081/doconnect/user/getall', {
      headers,
      responseType: 'json',
    });
  }

  getAllQuestions(username:any,password:any)
  {
    const headers = new HttpHeaders({
      Authorization: 'Basic ' + btoa(username + ':' + password),
    });

    return this.http.get('http://localhost:8081/doconnect/question/getall', {
      headers,
      responseType: 'json',
    });


  }

  getAllAnswers(username:any,password:any)
  {
    const headers = new HttpHeaders({
      Authorization: 'Basic ' + btoa(username + ':' + password),
    });
    return this.http.get('http://localhost:8081/doconnect/answer/getall', {
      headers,
      responseType: 'json',
    });

  }

  deleteUser(username: any, password: any,user:any)
  {
    const headers = new HttpHeaders({
      Authorization: 'Basic ' + btoa(username + ':' + password),
    });
    const user_id = user.user_id;
    return this.http.delete('http://localhost:8081/doconnect/user/' + user_id,{headers,responseType:'text'});
  }

  updateUser(username: any, password: any,user:any,newUser:any)
  {
    const headers = new HttpHeaders({
      Authorization: 'Basic ' + btoa(username + ':' + password),
    });
    const user_id = user.user_id;
    return this.http.put('http://localhost:8081/doconnect/user/' + user_id,newUser,{headers,responseType:'text'});
  }
}
