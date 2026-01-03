package com.doconnect.chatservice.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/*
 * @Author : Darshan Khairnar
 * Created Date : 27-8-2022
 * Modified Date : 29-8-2022
 * Description : Message Entity Class
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Message {

    private String name;
    private String content;
    
}
