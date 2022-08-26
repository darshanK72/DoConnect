package com.doconnect.doconnectservice.services;

import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.doconnect.doconnectservice.dto.QuestionDTO;
import com.doconnect.doconnectservice.entity.Answer;
import com.doconnect.doconnectservice.entity.Question;
import com.doconnect.doconnectservice.entity.User;
import com.doconnect.doconnectservice.repository.AnswerRepository;
import com.doconnect.doconnectservice.repository.QuestionRepository;
import com.doconnect.doconnectservice.repository.UserRepository;

@Service
public class QuestionServiceImpl implements QuestionService {

    @Autowired
    QuestionRepository questionrepository;

    @Autowired
    AnswerRepository answerRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    AnswerServiceImpl answerService;

    public String addQuestion(@Valid QuestionDTO questionDTO) {

        questionrepository.save(mapDtoToQuestion(questionDTO));
        return "Question Added Successfully";
    }

    public List<QuestionDTO> getAllQuestions() {
        List<Question> questionList = questionrepository.findAll();

        return questionList.stream().map(this::mapQuestionToDto).collect(Collectors.toList());
    }

    public QuestionDTO getQuestion(Long question_id)
    {
        Question question = this.questionrepository.findById(question_id).orElseThrow(() -> new RuntimeException("Error: question is not found."));
        return this.mapQuestionToDto(question);
    }


    private QuestionDTO mapQuestionToDto(Question question) {
        QuestionDTO questionDTO = new QuestionDTO();
        questionDTO.setQuestion_id(question.getQuestion_id());
        questionDTO.setQuestion(question.getQuestion());
        questionDTO.setDescription(question.getDescription());
        questionDTO.setTopic(question.getTopic());
        questionDTO.setUsername(question.getUser().getUsername());
        questionDTO.setUser_id(question.getUser().getUser_id());

        return questionDTO;

    }

    private Question mapDtoToQuestion(QuestionDTO questionDTO) {
        Question question = new Question();
        question.setQuestion(questionDTO.getQuestion());
        question.setDescription(questionDTO.getDescription());
        question.setTopic(questionDTO.getTopic());

        User user = userRepository.findById(questionDTO.getUser_id())
                .orElseThrow(() -> new RuntimeException("Error: user is not found."));
        question.setUser(user);

        return question;
    }

    public String deleteQuestion(Long question_id) {
        Question ques = questionrepository.findById(question_id)
            .orElseThrow(() -> new RuntimeException("Error: question is not found."));

        List<Answer> answers = this.answerRepository.findAllByQuestion(ques)
            .orElseThrow(() -> new RuntimeException("Error : Answer is not found"));

        
        ques.setUser(null);
        answers.forEach(this::setAnswerUserToNull);

        questionrepository.deleteById(question_id);
        return "Question deleted succesfully";

    }

    private void setAnswerUserToNull(Answer answer) {
        this.answerService.deleteAnswer(answer.getAnswer_id());
    }

}
