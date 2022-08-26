import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.css']
})
export class HeaderComponent implements OnInit {


  constructor() { }

  ngOnInit(): void {
  }

  isLoggedIn()
  {
    const username = window.localStorage.getItem("username");
    if(username == null || username == undefined)
    {
      return false;
    }
    return true;

  }

}
