package com.doconnect.doconnectservice.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.doconnect.doconnectservice.dto.QuestionDTO;
import com.doconnect.doconnectservice.entity.Question;
import com.doconnect.doconnectservice.services.QuestionService;

@RestController
@RequestMapping("/question")
public class QuestionController {

    @Autowired
    QuestionService questionService;

    @PostMapping("/addquestion")
    public ResponseEntity<String> addQuestion(@Valid @RequestBody QuestionDTO questionDTO)
    {
        return ResponseEntity.ok(this.questionService.addQuestion(questionDTO));
    }

    @GetMapping("/get")
    public List<Question> getAllQuestions()
    {
        return questionService.getAllQuestions();
    }

    @DeleteMapping("/{question_id}")
    public void deleteQuestion(@PathVariable Long question_id) {
		
       questionService.deleteQuestion(question_id);
       
	}
    
}
