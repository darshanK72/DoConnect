import { Component, OnInit } from '@angular/core';
import { NgForm } from '@angular/forms';
import { Router } from '@angular/router';
import { AuthserviceService } from 'src/app/Service/authservice.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  username!: string;
  password!:string;
  message:any;
  

  constructor(private authService:AuthserviceService,private router:Router) { }

  ngOnInit(): void {
  }

  doLogin(myform:NgForm)
  {
    const username = myform.value.username;
    const password = myform.value.password;
    this.authService.login(username,password).subscribe(data => 
      {
        this.message = data;
        console.log(this.message);
        window.localStorage.setItem("username",this.message.username);
        window.localStorage.setItem("firstName",this.message.firstName);
        window.localStorage.setItem("lastName",this.message.lastName);
        window.localStorage.setItem("password",password);
        window.localStorage.setItem("email",this.message.email);
        window.localStorage.setItem("phone",this.message.phone);
        window.localStorage.setItem("user_id",this.message.user_id);
        window.localStorage.setItem("roles",this.message.roles);

        this.router.navigate(["/home"]);
      })

  }
}
