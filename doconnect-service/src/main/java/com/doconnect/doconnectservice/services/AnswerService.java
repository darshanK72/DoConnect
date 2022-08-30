package com.doconnect.doconnectservice.services;

import java.util.List;

import javax.validation.Valid;

import com.doconnect.doconnectservice.dto.AnswerDTO;

/*
 * @Author : Samarthan Reddy
 * Created Date : 25-08-2022
 * Modified Date : 28-08-2022
 * Description : Provided details of Answers 
 * Params : None
 * Return Type : None
 */

public interface AnswerService {

    public String addAnswer(@Valid AnswerDTO answerDTO);
    public List<AnswerDTO> getAllAnswers();
    public List<AnswerDTO> getAllAnswersOfQuestion(Long question_id);
    public AnswerDTO getAnswer(Long answer_id);
    public String deleteAnswer(Long answer_id);
    public List<AnswerDTO> getAllAnswersOfUser(Long user_id);
}
