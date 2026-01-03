import { Component, OnInit } from '@angular/core';
import { NgForm } from '@angular/forms';
import { Router } from '@angular/router';
import { ToastrService } from 'ngx-toastr';
import { AuthserviceService } from 'src/app/Service/authservice.service';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})
export class RegisterComponent implements OnInit {

  username!: string;
  password!:string;
  firstName!:string;
  lastName!:string;
  email!:string;
  phone!:string;

  constructor(private authService:AuthserviceService,private router:Router,private toastr:ToastrService) { }

  ngOnInit(): void {
  }

  doRegister(myform:NgForm)
  {
    let regObj = {
      username:myform.value.username,
      password:myform.value.password,
      firstName:myform.value.firstName,
      lastName:myform.value.lastName,
      email:myform.value.email,
      phone:myform.value.phone,
    }

    this.authService.register(regObj).subscribe(data => {
      this.toastr.success("Registered Successfully","Success",{positionClass:'toast-bottom-right'})
      this.router.navigate(['/login']);
    } )

  }

}
