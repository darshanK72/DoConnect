import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { LoginComponent } from './Auth/login/login.component';
import { LogoutComponent } from './Auth/logout/logout.component';
import { RegisterComponent } from './Auth/register/register.component';
import { UpdateUserComponent } from './Auth/update-user/update-user.component';
import { AdminDashboardComponent } from './Layout/admin-dashboard/admin-dashboard.component';
import { AllusersComponent } from './Layout/allusers/allusers.component';
import { ChatboxComponent } from './Layout/chatbox/chatbox.component';
import { DashboardComponent } from './Layout/dashboard/dashboard.component';
import { HomeComponent } from './Layout/home/home.component';

const routes: Routes = [
  {
    path: 'dash',
    component: DashboardComponent,
  },
  {
    path:"",
    redirectTo:"home",
    pathMatch:"full"
  },
  {
    path:"chatbox",
    component:ChatboxComponent,
  },
  {
    path:"home",
    component: HomeComponent
  },
  {
    path:"login",component:LoginComponent
  },
  {
    path:"logout",component:LogoutComponent
  },
  {
    path:"allusers",component:AllusersComponent
  },
  {
    path:"register",component:RegisterComponent
  },
  {
    path:"admin_dashboard",
    component:AdminDashboardComponent
  },
  {
    path:"update_user",
    component:UpdateUserComponent
  }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule],
})
export class AppRoutingModule {}
