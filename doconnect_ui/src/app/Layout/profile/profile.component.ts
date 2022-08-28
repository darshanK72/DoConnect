import { Component, OnInit } from '@angular/core';
import { AnswerServiceService } from 'src/app/Service/answer-service.service';
import { QuestionServiceService } from 'src/app/Service/question-service.service';

@Component({
  selector: 'app-profile',
  templateUrl: './profile.component.html',
  styleUrls: ['./profile.component.css']
})
export class ProfileComponent implements OnInit {

  username!: any;
  password!:any;
  firstName!:any;
  lastName!:any;
  email!:any;
  phone!:any;
  user_id!:any;

  // role_user!:string;
  // role_admin!:string;
  // user:any;

  questionList!:any;
  answerList!:any;

  constructor(private questionService:QuestionServiceService,private answerService:AnswerServiceService) { }

  ngOnInit(): void {
    this.username = window.localStorage.getItem("username");
    this.firstName= window.localStorage.getItem("firstName");
    this.password = window.localStorage.getItem("password");
    this.lastName= window.localStorage.getItem("lastName");
    this.email= window.localStorage.getItem("email");
    this.phone= window.localStorage.getItem("phone");
    this.user_id = window.localStorage.getItem("user_id");

    this.getAnswersOfUser();
    this.getQuestionsOfUser();
  }

  getQuestionsOfUser()
  {
    this.questionService.getQuestionsOfUser(this.username,this.password,this.user_id).subscribe(data =>
      {
        this.questionList = data;
      })
  }

  getAnswersOfUser()
  {
    this.answerService.getAnswersOfUser(this.username,this.password,this.user_id).subscribe(data =>
      {
        this.answerList = data;

      })

  }

}
