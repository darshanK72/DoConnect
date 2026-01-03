import { Component, Input, OnInit } from '@angular/core';

@Component({
  selector: 'app-dashboard',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {

  isClickOnAnswer = false;
  constructor() { }

  searchText='';
  message = '';
  ngOnInit(): void {
    this.message = this.searchText;
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

  getQueryQuestions()
  {

  }
  getAnswers()
  {
    return this.isClickOnAnswer;
  }

}
