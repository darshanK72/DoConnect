import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class QuestionServiceService {

  constructor(private http:HttpClient) { }

  getAllQuestions(username:any,password:any) {
    // const headers = new HttpHeaders({
    //   Authorization: 'Basic ' + btoa(username + ':' + password),
    // });
    return this.http.get('http://localhost:8585/doconnect/question/getAllApprovedQuestions', {responseType: 'json'});
  }

  addQuestion(qnObj:any)
  {
    const username = window.localStorage.getItem("username");
    const password = window.localStorage.getItem("password");
    const headers = new HttpHeaders({
      Authorization: 'Basic ' + btoa(username + ':' + password),
    });
    return this.http.post('http://localhost:8081/doconnect/question/addquestion',qnObj,{headers,responseType:'text'});
  }

  deleteQuestion(username:any,password:any,question:any)
  {
    const headers = new HttpHeaders({
      Authorization: 'Basic ' + btoa(username + ':' + password),
    });
    const question_id = question.question_id;
    return this.http.delete('http://localhost:8081/doconnect/question/' + question_id,{headers,responseType:'text'});

  }

  approveQuestion(username:any,password:any,question:any)
  {
    const headers = new HttpHeaders({
      Authorization: 'Basic ' + btoa(username + ':' + password),
    });
    const question_id = question.question_id;
    return this.http.get('http://localhost:8081/doconnect/question/approve/' + question_id,{headers,responseType:'text'});
  }

}
