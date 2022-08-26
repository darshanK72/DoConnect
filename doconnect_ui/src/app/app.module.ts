import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { HeaderComponent } from './Layout/header/header.component';
import { FooterComponent } from './Layout/footer/footer.component';
import { DashboardComponent } from './Layout/dashboard/dashboard.component';
import { LoginComponent } from './Auth/login/login.component';
import { LogoutComponent } from './Auth/logout/logout.component';
import { RegisterComponent } from './Auth/register/register.component';
import { AuthserviceService } from './Service/authservice.service';
import { FormsModule } from '@angular/forms';
import {HttpClientModule} from '@angular/common/http';
import { AllusersComponent } from './Layout/allusers/allusers.component';
import { HomeComponent } from './Layout/home/home.component';
import { ChatboxComponent } from './Layout/chatbox/chatbox.component'

@NgModule({
  declarations: [
    AppComponent,
    HeaderComponent,
    FooterComponent,
    DashboardComponent,
    LoginComponent,
    LogoutComponent,
    RegisterComponent,
    AllusersComponent,
    HomeComponent,
    ChatboxComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    FormsModule,
    HttpClientModule
  ],
  providers: [AuthserviceService],
  bootstrap: [AppComponent]
})
export class AppModule { }
