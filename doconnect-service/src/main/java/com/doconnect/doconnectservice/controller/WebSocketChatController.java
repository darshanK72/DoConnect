package com.doconnect.doconnectservice.controller;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;

import com.doconnect.doconnectservice.entity.Message;

@Controller
public class WebSocketChatController {

    @MessageMapping("/resume")
    @SendTo("/start/initial")
    public Message chat(@RequestBody Message msg) {
        System.out.println(msg);
        return msg;
    }
    
}
