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

import com.doconnect.doconnectservice.dto.AnswerDTO;
import com.doconnect.doconnectservice.services.AnswerService;

@RestController
@RequestMapping("/answer")
public class AnswerController {

   @Autowired
   AnswerService answerService;

   @GetMapping("/getall")
   public ResponseEntity<List<AnswerDTO>> getAllAnswers() {
      return ResponseEntity.ok(this.answerService.getAllAnswers());
   }

   @GetMapping("/{answer_id}")
   public ResponseEntity<AnswerDTO> getAnswer(@PathVariable Long answer_id)
   {
      return ResponseEntity.ok(this.answerService.getAnswer(answer_id));
   }

   @GetMapping("/question/{question_id}")
   public ResponseEntity<List<AnswerDTO>> getAllAnswersOfQuestion(@PathVariable Long question_id)
   {
      return ResponseEntity.ok(this.answerService.getAllAnswersOfQuestion(question_id));
   }

   @PostMapping("/addanswer")
   public ResponseEntity<String> addAnswer(@Valid @RequestBody AnswerDTO answerDTO) {
      return ResponseEntity.ok(this.answerService.addAnswer(answerDTO));
   }

   @DeleteMapping("/{answer_id}")
   public void deleteAnswer(@PathVariable Long answer_id) {

      answerService.deleteAnswer(answer_id);

   }

}
