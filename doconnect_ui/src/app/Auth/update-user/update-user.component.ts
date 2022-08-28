import { Location } from '@angular/common';
import { Component, OnInit } from '@angular/core';
import { NgForm } from '@angular/forms';
import { Router } from '@angular/router';
import { ToastrService } from 'ngx-toastr';
import { AuthserviceService } from 'src/app/Service/authservice.service';

@Component({
  selector: 'app-update-user',
  templateUrl: './update-user.component.html',
  styleUrls: ['./update-user.component.css']
})
export class UpdateUserComponent implements OnInit {

  username!: string;
  password!:string;
  firstName!:string;
  lastName!:string;
  email!:string;
  phone!:string;

  role_user!:string;
  role_admin!:string;
  user:any;

  constructor(private authService:AuthserviceService,private router:Router,private location:Location,private toastr:ToastrService) { }

  ngOnInit(): void {
    this.user = this.location.getState();
    this.username = this.user.username;
    this.firstName= this.user.firstName;
    this.lastName= this.user.lastName;
    this.email= this.user.email;
    this.phone= this.user.phone;
  }

  updateUser(myform:NgForm)
  {
    const rs = [];
    if(myform.value.role_user == true)
    {
      rs.push("user");
    }
    if(myform.value.role_admin == true)
    {
      rs.push("admin");
    }
    let regObj = {
      username:myform.value.username,
      password:myform.value.password,
      firstName:myform.value.firstName,
      lastName:myform.value.lastName,
      email:myform.value.email,
      phone:myform.value.phone,
      roles:rs

    }

    console.log(regObj);
    const username = window.localStorage.getItem("username");
    const password = window.localStorage.getItem("password");
    this.authService.updateUser(username,password,this.user,regObj).subscribe(data => {
      this.toastr.success(data);
      this.router.navigate(['/admin_dashboard']);
    })

  }

}
