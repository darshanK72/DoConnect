package com.doconnect.doconnectservice.services;

import java.util.List;

import javax.validation.Valid;

import com.doconnect.doconnectservice.dto.QuestionDTO;


/*
 * @Author : Rahul Chilampande
 * Created Date : 25-8-2022
 * Modified Date : 28-8-2022
 */

public interface QuestionService {

    public String addQuestion(@Valid QuestionDTO questionDTO);
    public List<QuestionDTO> getAllQuestions();
    public QuestionDTO getQuestion(Long question_id);
    public String deleteQuestion(Long question_id);
    public List<QuestionDTO> getAllQuestionsOfUser(Long user_id);
    
}
