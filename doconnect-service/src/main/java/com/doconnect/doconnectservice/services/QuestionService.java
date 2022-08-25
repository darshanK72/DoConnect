package com.doconnect.doconnectservice.services;

import java.util.HashSet;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.doconnect.doconnectservice.dto.QuestionDTO;
import com.doconnect.doconnectservice.entity.Question;
import com.doconnect.doconnectservice.entity.User;
import com.doconnect.doconnectservice.repository.QuestionRepository;
import com.doconnect.doconnectservice.repository.UserRepository;
@Service
public class QuestionService {

    @Autowired
    QuestionRepository questionrepository;

    @Autowired
    UserRepository userRepository;

    public String registerQuestion(@Valid QuestionDTO questionDTO) {
            Question question=new Question();
            User user = userRepository.findById(questionDTO.getUser_id()).orElseThrow(() -> new RuntimeException("Error: user is not found."));
            question.setDescription(questionDTO.getDescription());
            question.setQuestion(questionDTO.getQuestion());
            question.setTopic(questionDTO.getTopic());
            question.setUser(user);
            question.setAnswers(new HashSet<>());
            
            questionrepository.save(question);
            return "question";
    }

    public List<Question> getAllQuestions() {
        return questionrepository.findAll();
    }

    public String deleteQuestion(Long question_id)
    {
        Question ques= questionrepository.findById(question_id).orElseThrow(() -> new RuntimeException("Error: question is not found."));
        ques.setUser(null);
        questionrepository.deleteById(question_id);
       return "Question deleted succesfully";
        
    }
    
}
