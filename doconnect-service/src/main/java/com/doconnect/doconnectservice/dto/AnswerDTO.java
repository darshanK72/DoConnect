package com.doconnect.doconnectservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AnswerDTO {

 private Long answer_id;
 private String answer;
 private String username;
 private Long question_id;
 private Long user_id;


}
