import { Component, OnInit } from '@angular/core';
import { NgForm } from '@angular/forms';
import { ToastrService } from 'ngx-toastr';
import { AnswerServiceService } from 'src/app/Service/answer-service.service';
import { QuestionServiceService } from 'src/app/Service/question-service.service';

@Component({
  selector: 'app-question-holder',
  templateUrl: './question-holder.component.html',
  styleUrls: ['./question-holder.component.css']
})
export class QuestionHolderComponent implements OnInit {

  questions!:any;
  answers!:any;
  image!:any;
  message!:any;


  isClickOnAnswer = false;
  isQuestionId:any;

  isClickOnAddAnswer = false;
isQuestionAnsId: any;

  constructor(private questionService:QuestionServiceService,private answerService:AnswerServiceService,private toastr:ToastrService) { }

  ngOnInit(): void {
    this.getAllQuestions();
  }

  addAnswer(myform:NgForm,question:any)
  {
    let ansObj = {
      answer:myform.value.message,
      user_id:window.localStorage.getItem("user_id"),
      question_id:question.question_id
    }

    this.answerService.addAnswer(ansObj).subscribe(data =>
      {
        this.toastr.success(data);
      },error =>
      {
        this.toastr.error('You are not logged in, Please logged in to answer questions!');
      });

      myform.reset();
      this.isClickOnAddAnswer = false;
  }

  showTextbox(question:any)
  {
    if(this.isClickOnAddAnswer == true && question.question_id == this.isQuestionAnsId )
    {
      this.isClickOnAddAnswer = false;
    }
    else{
      this.isClickOnAddAnswer = true;
      this.isQuestionAnsId = question.question_id; 
    }

  }
  yesShowTextbox(question:any)
  {
    if((question.question_id == this.isQuestionAnsId) && this.isClickOnAddAnswer)
    {
      return true;
    }
    return false;

  }

  showAnswer(question: any)
  {
      if(this.isClickOnAnswer == true && question.question_id == this.isQuestionId)
      {
        this.isClickOnAnswer = false;
      }
      else{

        this.isClickOnAnswer = true;
        this.isQuestionId = question.question_id; 
        this.answerService.getAllAnswers(question).subscribe(data => {
          this.answers = data;
    
        });
      }

  }

  isClicked(question:any)
  {
    if((question.question_id == this.isQuestionId) && this.isClickOnAnswer)
    {
      return true;
    }
    return false;
  }


  getAllQuestions()
  {
    const username = window.localStorage.getItem("username");
    const password = window.localStorage.getItem("password");
    this.questionService.getAllQuestions(username,password).subscribe(data =>
      {
        this.questions = data;
      })
  }
}
