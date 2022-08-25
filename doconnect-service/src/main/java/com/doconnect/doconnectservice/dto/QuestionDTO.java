package com.doconnect.doconnectservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class QuestionDTO {
    
    private Long question_id;
    private String question;
    private String description;
    private String topic;
    private String username;
    private Long user_id;
}
