package com.doconnect.doconnectservice.services;

import java.util.List;

import javax.print.DocFlavor.STRING;
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

   Answer answer = new Answer();
   User user = userRepository.findById(answerDTO.getUser_id())
     .orElseThrow(() -> new RuntimeException("Error : User Not Found"));

   Question question = questionRepository.findById(answerDTO.getQuestion_id())
     .orElseThrow(() -> new RuntimeException("Error : Question is not find"));

   answer.setQuestion(question);
   answer.setAnswer(answerDTO.getAnswer());
   answer.setUser(user);

   answerRepository.save(answer);
   return "answer";

  }
  
  public List<Answer> getAllanswer() {
   return answerRepository.findAll();
  }

  public String deleteAnswer(Long answer_id) {
      Answer answer= answerRepository.findById(answer_id).orElseThrow(() -> new RuntimeException("Error: Answer is not found."));
      answer.setUser(null);
        answer.setQuestion(null);
        answerRepository.deleteById(answer_id);
       return "Answer deleted succesfully";

  }
 

}
