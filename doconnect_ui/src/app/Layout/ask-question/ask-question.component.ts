import { Component, OnInit } from '@angular/core';
import { NgForm } from '@angular/forms';
import { QuestionServiceService } from 'src/app/Service/question-service.service';

@Component({
  selector: 'app-ask-question',
  templateUrl: './ask-question.component.html',
  styleUrls: ['./ask-question.component.css']
})
export class AskQuestionComponent implements OnInit {

  question!:string;
  description!:string;
  topic!:string;

  constructor(private questionService:QuestionServiceService) { }

  ngOnInit(): void {
  }

  addQuestion(myform:NgForm)
  {
    let qnObj = {
      question:myform.value.question,
      description:myform.value.description,
      topic:myform.value.topic,
      user_id:window.localStorage.getItem("user_id")
    }
    this.questionService.addQuestion(qnObj).subscribe(data =>
      {
        console.log(data);
      })

      myform.reset();
  }

}
