import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-dashboard',
  templateUrl: './dashboard.component.html',
  styleUrls: ['./dashboard.component.css']
})
export class DashboardComponent implements OnInit {

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
