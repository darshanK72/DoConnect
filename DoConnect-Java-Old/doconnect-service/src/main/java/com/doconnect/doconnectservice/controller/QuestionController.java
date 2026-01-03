package com.doconnect.doconnectservice.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.doconnect.doconnectservice.dto.AnswerDTO;
import com.doconnect.doconnectservice.dto.QuestionDTO;
import com.doconnect.doconnectservice.services.QuestionServiceImpl;

/*
 * @Author : Rahul Chilampande
 * Created Date : 25-08-2022
 * Modified Date : 30-08-2022
 * Description : Question Controller
 */

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/doconnect/question")
public class QuestionController {

    @Autowired
    QuestionServiceImpl questionService;

    @GetMapping("/getall")
    public ResponseEntity<List<QuestionDTO>> getAllQuestions()
    {
        return ResponseEntity.ok(questionService.getAllQuestions());
    }

    @GetMapping("/approve/{question_id}")
    public ResponseEntity<String> approveQuestion(@PathVariable Long question_id)
    {
        return ResponseEntity.ok(questionService.approveQuestion(question_id));
    }

    @GetMapping("/getAllApprovedQuestions")
   public ResponseEntity<List<QuestionDTO>> getAllApprovedQuestions()
   {
      return ResponseEntity.ok(questionService.getAllApprovedQuestions());
   }

   @GetMapping("/getAllQuestionByQuery/{query}")
   public ResponseEntity<List<QuestionDTO>> getAllQuestionsByQuery(@PathVariable String query)
   {
        return ResponseEntity.ok(questionService.getAllQuestionsByQuery(query));
   }

   @GetMapping("/getAllApprovedAnswersOfQuestions/{question_id}")
   public ResponseEntity<List<AnswerDTO>> getAllApprovedAnswersOfQuestions(@PathVariable Long question_id)
   {
    return ResponseEntity.ok(questionService.getAllApprovedAnswerOfQuestion(question_id));
   }

    @GetMapping("/{question_id}")
    public ResponseEntity<QuestionDTO> getQuestion(@PathVariable Long question_id)
    {
        return ResponseEntity.ok(questionService.getQuestion(question_id));
    }

    @GetMapping("/user/{user_id}")
    public ResponseEntity<List<QuestionDTO>> getAllQuestionsOfUser(@PathVariable Long user_id)
    {
        return ResponseEntity.ok(questionService.getAllQuestionsOfUser(user_id));
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
