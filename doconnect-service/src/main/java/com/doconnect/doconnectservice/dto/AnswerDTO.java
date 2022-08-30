package com.doconnect.doconnectservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/*
 * @Author : Samarthan Reddy
 * Created Date : 25-08-2022
 * Modified Date : 28-08-2022
 * Description : Created answer DTO(Data Transfer Object)
 * Params : None
 * Return Type : None
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
