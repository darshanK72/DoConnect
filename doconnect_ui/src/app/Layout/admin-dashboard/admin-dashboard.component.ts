import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { ToastrService } from 'ngx-toastr';
import { AnswerServiceService } from 'src/app/Service/answer-service.service';
import { AuthserviceService } from 'src/app/Service/authservice.service';
import { QuestionServiceService } from 'src/app/Service/question-service.service';

@Component({
  selector: 'app-admin-dashboard',
  templateUrl: './admin-dashboard.component.html',
  styleUrls: ['./admin-dashboard.component.css']
})
export class AdminDashboardComponent implements OnInit {

  isClickedOnUsers = false;
  isClickedOnQuestions = false;
  isClickedOnAnswers = false;
  userList:any;
  questionList:any;
  answerList:any;
  constructor(private authService:AuthserviceService,private questionService:QuestionServiceService,private answerService:AnswerServiceService,private toastr:ToastrService,private router:Router) { }

  ngOnInit(): void {
  }
  getUsers()
  {
    const username = window.localStorage.getItem("username");
    const password = window.localStorage.getItem("password");
    this.isClickedOnUsers = true;
    this.isClickedOnQuestions = false;
    this.isClickedOnAnswers = false;
    this.authService.getAllUsers(username,password).subscribe(data =>
      {
        this.userList = data;
      })
  }

  deleteUser(user:any)
  {
    const username = window.localStorage.getItem("username");
    const password = window.localStorage.getItem("password");
    this.authService.deleteUser(username,password,user).subscribe(data =>
      {
        this.toastr.success(data);
      })
  }

  updateUser(user:any)
  {
    const username = window.localStorage.getItem("username");
    const password = window.localStorage.getItem("password");
    // this.authService.updateUser(username,password,user).subscribe(data =>
    //   {
    //     this.toastr.success(data);
    //   })

    this.router.navigateByUrl('/update_user',{state:user});
  }

  getQuestions()
  {
    this.isClickedOnUsers = false
    this.isClickedOnQuestions = true;
    this.isClickedOnAnswers = false;
    const username = window.localStorage.getItem("username");
    const password = window.localStorage.getItem("password");
    this.authService.getAllQuestions(username,password).subscribe(data =>
      {
        this.questionList = data;
        console.log(data);
      })

  }

  deleteQuestion(question:any)
  {
    const username = window.localStorage.getItem("username");
    const password = window.localStorage.getItem("password");
    this.questionService.deleteQuestion(username,password,question).subscribe(data =>
      {
        this.toastr.success(data);
      })
  }

  approveQuestion(question:any)
  {
    const username = window.localStorage.getItem("username");
    const password = window.localStorage.getItem("password");
    this.questionService.approveQuestion(username,password,question).subscribe(data =>
      {
        this.toastr.success(data);
      })
  }


  deleteAnswer(answer:any)
  {
    const username = window.localStorage.getItem("username");
    const password = window.localStorage.getItem("password");
    this.answerService.deleteAnswer(username,password,answer).subscribe(data =>
      {
        this.toastr.success(data);
      });
  }

  approveAnswer(answer:any)
  {
    const username = window.localStorage.getItem("username");
    const password = window.localStorage.getItem("password");
    this.answerService.approveAnswer(username,password,answer).subscribe(data =>
      {
        this.toastr.success(data);
      })

  }
  getAnswers()
  {
    this.isClickedOnUsers = false;
    this.isClickedOnAnswers = true;
    this.isClickedOnQuestions = false;
    const username = window.localStorage.getItem("username");
    const password = window.localStorage.getItem("password");
    this.authService.getAllAnswers(username,password).subscribe(data =>
      {
        this.answerList = data;
        console.log(data);
      })
  }

  isApprovedQuestoin(question:any)
  {
    return question.approve;
  }

  isApprovedAnswer(answer:any)
  {
    return answer.approve;
  }
}
