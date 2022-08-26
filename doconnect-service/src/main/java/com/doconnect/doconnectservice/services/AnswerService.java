package com.doconnect.doconnectservice.services;

import java.util.List;

import javax.validation.Valid;

import com.doconnect.doconnectservice.dto.AnswerDTO;

public interface AnswerService {

    public String addAnswer(@Valid AnswerDTO answerDTO);
    public List<AnswerDTO> getAllAnswers();
    public List<AnswerDTO> getAllAnswersOfQuestion(Long question_id);
    public AnswerDTO getAnswer(Long answer_id);
    public String deleteAnswer(Long answer_id);
}
