import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { ToastrService } from 'ngx-toastr';

@Component({
  selector: 'app-logout',
  templateUrl: './logout.component.html',
  styleUrls: ['./logout.component.css']
})
export class LogoutComponent implements OnInit {

  constructor(private router:Router,private toastr:ToastrService) { }

  ngOnInit(): void {
  }

  logout()
  {
    window.localStorage.clear();
    this.toastr.success("You Logged Out","Success",{positionClass:'toast-bottom-right'})
    this.router.navigate(['/home']);

  }

}
