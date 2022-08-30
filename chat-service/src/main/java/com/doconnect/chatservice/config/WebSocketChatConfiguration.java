package com.doconnect.chatservice.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;


/*
 * @Author : Darshan Khairnar
 * Created Date : 27-8-2022
 * Modified Date : 29-8-2022
 * Description : WebSocket Config class implements WebSocketMessageBrokerConfigurer
 */
@Configuration
@EnableWebSocketMessageBroker
public class WebSocketChatConfiguration implements WebSocketMessageBrokerConfigurer {

    @Override
    public void configureMessageBroker(MessageBrokerRegistry config) {
        config.enableSimpleBroker("/start");
        config.setApplicationDestinationPrefixes("/current");  
    }

    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        registry
        .addEndpoint("/testchat")
        .setAllowedOrigins("http://localhost:4200")
        .withSockJS();

    }
}
