package com.doconnect.doconnectservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/*
 * @Author : Rahul Chilampande
 * Created Date : 25-08-2022
 * Modified Date : 30-08-2022
 * Description : Question Dto class
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class QuestionDTO {
    
    private Long question_id;
    private String question;
    private String description;
    private String topic;
    private String username;
    private boolean approve;
    private Long user_id;
}
