package com.doconnect.doconnectservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AnswerDTO {
 
 private String answer;
 private Long question_id;
 private long user_id;


}
