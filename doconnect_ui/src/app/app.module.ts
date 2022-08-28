import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { HeaderComponent } from './Layout/header/header.component';
import { FooterComponent } from './Layout/footer/footer.component';
import { LoginComponent } from './Auth/login/login.component';
import { LogoutComponent } from './Auth/logout/logout.component';
import { RegisterComponent } from './Auth/register/register.component';
import { AuthserviceService } from './Service/authservice.service';
import { FormsModule } from '@angular/forms';
import {HttpClientModule} from '@angular/common/http';
import { HomeComponent } from './Layout/home/home.component';
import { ChatboxComponent } from './Layout/chatbox/chatbox.component';
import { QuestionHolderComponent } from './Layout/question-holder/question-holder.component';
import { AskQuestionComponent } from './Layout/ask-question/ask-question.component'
import {BrowserAnimationsModule} from '@angular/platform-browser/animations'
import { ToastrModule } from 'ngx-toastr';
import { AdminDashboardComponent } from './Layout/admin-dashboard/admin-dashboard.component';
import { UpdateUserComponent } from './Auth/update-user/update-user.component';
import { ProfileComponent } from './Layout/profile/profile.component';
@NgModule({
  declarations: [
    AppComponent,
    HeaderComponent,
    FooterComponent,
    LoginComponent,
    LogoutComponent,
    RegisterComponent,
    HomeComponent,
    ChatboxComponent,
    QuestionHolderComponent,
    AskQuestionComponent,
    AdminDashboardComponent,
    UpdateUserComponent,
    ProfileComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    FormsModule,
    HttpClientModule,
    BrowserAnimationsModule,
    ToastrModule.forRoot()
  ],
  providers: [AuthserviceService],
  bootstrap: [AppComponent]
})
export class AppModule { }
