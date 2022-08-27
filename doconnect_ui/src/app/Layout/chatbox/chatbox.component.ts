import { Component, OnInit } from '@angular/core';
import * as Stomp from 'stompjs';
import * as SockJS from 'sockjs-client';


@Component({
  selector: 'app-chatbox',
  templateUrl: './chatbox.component.html',
  styleUrls: ['./chatbox.component.css']
})
export class ChatboxComponent implements OnInit {

  greetings:any[] = [];
  tempg = {
    name: '',
    content : ''
  };
  disabled = true;
  newmessage: any;
  stompClient:any;

  constructor() { }

  ngOnInit(): void {
    this.connect();
  }

  setConnected(connected: boolean) {
    this.disabled = !connected;
    if (connected) {
      this.greetings = [];
    }
  }
  connect() {
    const socket = new SockJS('http://localhost:9090/testchat');
    this.stompClient = Stomp.over(socket);
    const _this = this;
    this.stompClient.connect({}, function (frame: string) {
      console.log('Connected: ' + frame);
      _this.stompClient.subscribe('/start/initial', function(response:any){
        // console.log(response);
        _this.showMessage(JSON.parse(response.body));
      });
   });
  }
  sendMessage() {

    const mesObj = {
      name:window.localStorage.getItem("username"),
      content:this.newmessage
    }
    this.stompClient.send(
      '/current/resume',
      {},
      JSON.stringify(mesObj)
    );
    mesObj;
  }
  showMessage(message:any) {
    this.greetings.push(message);
  }

}
