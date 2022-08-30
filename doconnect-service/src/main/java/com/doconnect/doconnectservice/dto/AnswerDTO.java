package com.doconnect.doconnectservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/*
 * @Author : Rahul Chilampande
 * Created Date : 25-08-2022
 * Modified Date : 30-08-2022
 * Description : User Dto class
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AnswerDTO {

 private Long answer_id;
 private String answer;
 private String username;
 private Long question_id;
 private Long user_id;
 private boolean approve;


}
