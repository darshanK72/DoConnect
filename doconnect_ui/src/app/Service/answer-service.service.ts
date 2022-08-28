import { HttpClient, HttpHeaders, HttpResponse } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs/internal/Observable';

@Injectable({
  providedIn: 'root'
})
export class AnswerServiceService {

  constructor(private http:HttpClient) { }

  getAllAnswers(question:any) {
    // const headers = new HttpHeaders({
    //   Authorization: 'Basic ' + btoa(username + ':' + password),
    // });
    return this.http.get('http://localhost:8585/doconnect/question/getAllApprovedAnswersOfQuestions/' + question.question_id, {responseType: 'json'});
  }

  addAnswer(ansObj:any)
  {
    const username = window.localStorage.getItem("username");
    const password = window.localStorage.getItem("password");
      const headers = new HttpHeaders({
          Authorization: 'Basic ' + btoa(username + ':' + password),
        });
      return this.http.post('http://localhost:8081/doconnect/answer/addanswer',ansObj, {headers,responseType: 'text'});
  }

  deleteAnswer(username:any,password:any,answer:any)
  {
    const headers = new HttpHeaders({
      Authorization: 'Basic ' + btoa(username + ':' + password),
    });
    const answer_id = answer.answer_id;
    return this.http.delete('http://localhost:8081/doconnect/answer/' + answer_id,{headers,responseType:'text'});

  }

  approveAnswer(username:any,password:any,answer:any)
  {
    const headers = new HttpHeaders({
      Authorization: 'Basic ' + btoa(username + ':' + password),
    });
    const answer_id = answer.answer_id;
    return this.http.get('http://localhost:8081/doconnect/answer/approve/' + answer_id,{headers,responseType:'text'});
  }
}
