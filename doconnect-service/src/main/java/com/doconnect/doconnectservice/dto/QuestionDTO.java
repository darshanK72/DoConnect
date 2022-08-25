package com.doconnect.doconnectservice.dto;

import java.util.Set;



import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class QuestionDTO {
    
    private String question;
    private String description;
    private String topic;
    private Long user_id;
    private Set<String> answers;

    
}
