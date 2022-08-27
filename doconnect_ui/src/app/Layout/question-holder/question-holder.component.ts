import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-question-holder',
  templateUrl: './question-holder.component.html',
  styleUrls: ['./question-holder.component.css']
})
export class QuestionHolderComponent implements OnInit {

  isClickOnAnswer = false;
  constructor() { }

  ngOnInit(): void {
  }

  toggleAnswer()
  {
    if(this.isClickOnAnswer)
    {
      this.isClickOnAnswer = false;
    } 
    else
    {
      this.isClickOnAnswer = true;
    }
  }

  getAnswers()
  {
    return this.isClickOnAnswer;
  }
}
