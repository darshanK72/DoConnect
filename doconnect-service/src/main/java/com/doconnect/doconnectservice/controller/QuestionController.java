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
import com.doconnect.doconnectservice.services.QuestionService;

@RestController
@RequestMapping("/question")
public class QuestionController {

    @Autowired
    QuestionService questionService;

    @GetMapping("/getall")
    public ResponseEntity<List<QuestionDTO>> getAllQuestions()
    {
        return ResponseEntity.ok(questionService.getAllQuestions());
    }

    @GetMapping("/{question_id}")
    public ResponseEntity<QuestionDTO> getQuestion(@PathVariable Long question_id)
    {
        return ResponseEntity.ok(questionService.getQuestion(question_id));
    }

    @PostMapping("/addquestion")
    public ResponseEntity<String> addQuestion(@Valid @RequestBody QuestionDTO questionDTO)
    {
        return ResponseEntity.ok(this.questionService.addQuestion(questionDTO));
    }

    @DeleteMapping("/{question_id}")
    public ResponseEntity<String> deleteQuestion(@PathVariable Long question_id) {
       return ResponseEntity.ok(questionService.deleteQuestion(question_id));
       
	}
    
}
