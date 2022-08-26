import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-dashboard',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {

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
