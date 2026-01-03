package com.doconnect.chatservice.controller;


import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;

import com.doconnect.chatservice.entity.Message;


/*
 * @Author : Darshan Khairnar
 * Created Date : 27-8-2022
 * Modified Date : 29-8-2022
 * Description : Websocket Controller class for handiling messages
 */
@Controller
public class WebSocketChatController {

    @MessageMapping("/resume")
    @SendTo("/start/initial")
    public Message chat(@RequestBody Message msg) {
        System.out.println(msg);
        return msg;
    }
    
}
