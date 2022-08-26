import { Component, OnInit } from '@angular/core';
import { AuthserviceService } from 'src/app/Service/authservice.service';

@Component({
  selector: 'app-allusers',
  templateUrl: './allusers.component.html',
  styleUrls: ['./allusers.component.css']
})
export class AllusersComponent implements OnInit {

  constructor(private authService:AuthserviceService) { }

  ngOnInit(): void {
  }

  getUsers()
  {
    const username = window.localStorage.getItem("username");
    const password = window.localStorage.getItem("password");
    this.authService.getAllUsers(username,password).subscribe(data =>
      {
        console.log(data);
      })
  }

}
