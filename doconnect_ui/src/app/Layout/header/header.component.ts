import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.css']
})
export class HeaderComponent implements OnInit {

  isLoggedin = false;
  constructor() { }

  ngOnInit(): void {
  }

  isLoggedIn()
  {
    return this.isLoggedin;

  }

  logout()
  {
    alert("clicked on logout");
  }

}
