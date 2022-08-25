package com.doconnect.doconnectservice.services;

import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.doconnect.doconnectservice.dto.AnswerDTO;

import com.doconnect.doconnectservice.entity.Answer;
import com.doconnect.doconnectservice.entity.Question;
import com.doconnect.doconnectservice.entity.User;
import com.doconnect.doconnectservice.repository.AnswerRepository;
import com.doconnect.doconnectservice.repository.QuestionRepository;
import com.doconnect.doconnectservice.repository.UserRepository;

@Service
public class AnswerService {

  @Autowired
  UserRepository userRepository;

  @Autowired
  QuestionRepository questionRepository;

  @Autowired
  AnswerRepository answerRepository;

  public String addAnswer(@Valid AnswerDTO answerDTO) {
    answerRepository.save(this.mapDtoToAnswer(answerDTO));
    return "Answer Added Successfully";
  }

  public List<AnswerDTO> getAllAnswers() {
    List<Answer> answerList = this.answerRepository.findAll();
    return answerList.stream().map(this::mapAnswerToDto).collect(Collectors.toList());
  }

  public List<AnswerDTO> getAllAnswersOfQuestion(Long question_id)
  {
    Question question = this.questionRepository.findById(question_id) .orElseThrow(() -> new RuntimeException("Error : Question is not find"));
    List<Answer> answerList = this.answerRepository.findAllByQuestion(question).orElseThrow(() -> new RuntimeException("Error : Answers for question not found"));
    return answerList.stream().map(this::mapAnswerToDto).collect(Collectors.toList());
  }

  public AnswerDTO getAnswer(Long answer_id)
  {
    Answer answer = this.answerRepository.findById(answer_id).orElseThrow(() -> new RuntimeException("Error: Answer is not found."));

    return mapAnswerToDto(answer);
  }

  private Answer mapDtoToAnswer(AnswerDTO answerDTO)
  {
    Answer answer = new Answer();
    User user = userRepository.findById(answerDTO.getUser_id())
        .orElseThrow(() -> new RuntimeException("Error : User Not Found"));

    Question question = questionRepository.findById(answerDTO.getQuestion_id())
        .orElseThrow(() -> new RuntimeException("Error : Question is not find"));

    answer.setAnswer(answerDTO.getAnswer());
    answer.setUser(user);
    answer.setQuestion(question);

    return answer;
  }

  private AnswerDTO mapAnswerToDto(Answer answer) {

    AnswerDTO answerDTO = new AnswerDTO();
    answerDTO.setAnswer_id(answer.getAnswer_id());
    answerDTO.setAnswer(answer.getAnswer());
    answerDTO.setUsername(answer.getUser().getUsername());
    answerDTO.setQuestion_id(answer.getQuestion().getQuestion_id());
    answerDTO.setUser_id(answer.getUser().getUser_id());

    return answerDTO;

  }

  public String deleteAnswer(Long answer_id) {
    Answer answer = answerRepository.findById(answer_id)
        .orElseThrow(() -> new RuntimeException("Error: Answer is not found."));
    answer.setUser(null);
    answer.setQuestion(null);
    answerRepository.deleteById(answer_id);
    return "Answer deleted succesfully";

  }

}
