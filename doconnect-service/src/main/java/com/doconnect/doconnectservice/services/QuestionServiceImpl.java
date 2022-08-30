package com.doconnect.doconnectservice.services;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.doconnect.doconnectservice.dto.AnswerDTO;
import com.doconnect.doconnectservice.dto.QuestionDTO;
import com.doconnect.doconnectservice.dto.UserDTO;
import com.doconnect.doconnectservice.entity.Answer;
import com.doconnect.doconnectservice.entity.Question;
import com.doconnect.doconnectservice.entity.Role;
import com.doconnect.doconnectservice.entity.User;
import com.doconnect.doconnectservice.enums.ERoles;
import com.doconnect.doconnectservice.repository.AnswerRepository;
import com.doconnect.doconnectservice.repository.QuestionRepository;
import com.doconnect.doconnectservice.repository.UserRepository;

/*
 * @Author : Rahul Chilampande
 * Created Date : 25-08-2022
 * Modified Date : 28-08-2022
 * Description : Service Layer Implementation for Question
 */

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

    @Autowired
    EmailSenderService emailSenderService;

    // @Autowired
    // UserServiceImpl userServiceImpl;

    /*
     * @Author : Rahul Chilampande
     * Created Date : 25-08-2022
     * Modified Date : 30-08-2022
     * Description : Returning all admins
     * Params : None
     * Return Type : List<UserDTO>
     */

    public List<UserDTO> getAllAdmins() {
        List<User> userList = this.userRepository.findAll();

        List<UserDTO> adminList = new ArrayList<>();

        userList.forEach(user -> {
            Set<Role> roles = user.getRoles();
            roles.forEach(role -> {
                ERoles r = role.getRole();
                if (r == ERoles.ROLE_ADMIN) {
                    adminList.add(this.mapUserToDto(user));
                }
            });

        });

        return adminList;
    }
/*
     * @Author : Rahul Chilampande
     * Created Date : 25-08-2022
     * Modified Date : 30-08-2022
     * Description : Returning all Approved Questions
     * Params : None
     * Return Type : List<QuestionDTO>
     */
public List<QuestionDTO> getAllApprovedQuestions() {

    List<Question> questionList = this.questionrepository.findAllByIsApproved(true)
            .orElseThrow(() -> new RuntimeException("Error: Questions is not found."));
    return questionList.stream().map(this::mapQuestionToDto).collect(Collectors.toList());

}

    /*
     * @Author : Rahul Chilampande
     * Created Date : 25-08-2022
     * Modified Date : 30-08-2022
     * Description : Returning All Approved Answer Of Question
     * Params : question_id (Long)
     * Return Type : List<AnswerDTO>
     */

    public List<AnswerDTO> getAllApprovedAnswerOfQuestion(Long question_id) {
        Question question = this.questionrepository.findById(question_id)
                .orElseThrow(() -> new RuntimeException("Error: question is not found."));
        // List<Answer> answerList =
        // this.answerRepository.findAllByQuestion(question).orElseThrow(() -> new
        // RuntimeException("Error : Answers is not found"));

        List<Answer> answerList = this.answerRepository.findAllByIsApprovedAndQuestion(true, question)
                .orElseThrow(() -> new RuntimeException("Error : Answers is not found"));

        return answerList.stream().map(this::mapAnswerToDto).collect(Collectors.toList());
    }

/*
 * @Author : Rahul Chilampande
 * Created Date : 25-08-2022
 * Modified Date : 30-08-2022
 * Description : Mapping Answer to DTO
 * Params : Answer 
 * Return Type : AnswerDTO
 */
    
private AnswerDTO mapAnswerToDto(Answer answer) {

    AnswerDTO answerDTO = new AnswerDTO();
    answerDTO.setAnswer_id(answer.getAnswer_id());
    answerDTO.setAnswer(answer.getAnswer());
    answerDTO.setUsername(answer.getUser().getUsername());
    answerDTO.setQuestion_id(answer.getQuestion().getQuestion_id());
    answerDTO.setUser_id(answer.getUser().getUser_id());

    return answerDTO;

}

    /*
     * @Author : Rahul Chilampande
     * Created Date : 25-08-2022
     * Modified Date : 30-08-2022
     * Description : Adding question
     * Params : QuestionDTO 
     * Return Type : String
     */
    public String addQuestion(@Valid QuestionDTO questionDTO) {

        questionrepository.save(mapDtoToQuestion(questionDTO));

        String body = "New Question is added, Please Review the Question\n" +
                "Question : " + questionDTO.getQuestion() + "\n" +
                "Questioon Description : " + questionDTO.getDescription();

        String subject = "Question Added";

        List<UserDTO> adminList = this.getAllAdmins();
        adminList.forEach(admin -> {
            this.emailSenderService.sendMail(admin.getEmail(), body, subject);
        });

        return "Question Added Successfully";
    }

     /*
     * @Author : Rahul Chilampande
     * Created Date : 25-08-2022
     * Modified Date : 30-08-2022
     * Description : Returning All Questions
     * Params :none
     * Return Type : List<QuestionDTO> 
     */
     public List<QuestionDTO> getAllQuestions() {
         List<Question> questionList = questionrepository.findAll();

         return questionList.stream().map(this::mapQuestionToDto).collect(Collectors.toList());
     }

     /*
     * @Author : Rahul Chilampande
     * Created Date : 25-08-2022
     * Modified Date : 30-08-2022
     * Description : Returning All Questions By Query
     * Params : query(String )
     * Return Type : List<QuestionDTO>
     */
     public List<QuestionDTO> getAllQuestionsByQuery(String query) {
         List<Question> questionsList = questionrepository.findByDescriptionContainingIgnoreCase(query)
                 .orElseThrow(() -> new RuntimeException("Error: question is not found."));
         return questionsList.stream().map(this::mapQuestionToDto).collect(Collectors.toList());
     }

     /*
     * @Author : Rahul Chilampande
     * Created Date : 25-08-2022
     * Modified Date : 30-08-2022
     * Description : Returning All Questions Of User
     * Params : user_id (Long)
     * Return Type : List<QuestionDTO>
     */

     public List<QuestionDTO> getAllQuestionsOfUser(Long user_id) {
         User user = this.userRepository.findById(user_id)
                 .orElseThrow(() -> new RuntimeException("Error : User is not found"));
         List<Question> questionList = questionrepository.findAllByUser(user)
                 .orElseThrow(() -> new RuntimeException("Error: question is not found."));
         return questionList.stream().map(this::mapQuestionToDto).collect(Collectors.toList());
     }

     /*
     * @Author : Rahul Chilampande
     * Created Date : 25-08-2022
     * Modified Date : 30-08-2022
     * Description : Getting Question
     * Params : question_id(Long)
     * Return Type : QuestionDTO
     */

     public QuestionDTO getQuestion(Long question_id) {
         Question question = this.questionrepository.findById(question_id)
                 .orElseThrow(() -> new RuntimeException("Error: question is not found."));

         return this.mapQuestionToDto(question);
     }

     /*
     * @Author : Rahul Chilampande
     * Created Date : 25-08-2022
     * Modified Date : 30-08-2022
     * Description : Returning All Approve Question
     * Params : question_id(Long)
     * Return Type : String
     */

    public String approveQuestion(Long question_id) {
        Question question = this.questionrepository.findById(question_id)
                .orElseThrow(() -> new RuntimeException("Error: question is not found."));
        question.setApproved(true);
        this.questionrepository.save(question);
        return "Question Approved";
    }


     /*
     * @Author : Rahul Chilampande
     * Created Date : 25-08-2022
     * Modified Date : 30-08-2022
     * Description : Mapping Question To DTO
     * Params : Question 
     * Return Type : QuestionDTO
     */

     private QuestionDTO mapQuestionToDto(Question question) {
         QuestionDTO questionDTO = new QuestionDTO();
         questionDTO.setQuestion_id(question.getQuestion_id());
         questionDTO.setQuestion(question.getQuestion());
         questionDTO.setDescription(question.getDescription());
         questionDTO.setTopic(question.getTopic());
         questionDTO.setUsername(question.getUser().getUsername());
         questionDTO.setUser_id(question.getUser().getUser_id());
         questionDTO.setApprove(question.isApproved());

         return questionDTO;

     }
    
     /*
     * @Author : Rahul Chilampande
     * Created Date : 25-08-2022
     * Modified Date : 30-08-2022
     * Description : Mapping DTO To Question
     * Params : QuestionDTO 
     * Return Type : Question
     */

     private Question mapDtoToQuestion(QuestionDTO questionDTO) {
         Question question = new Question();
         question.setQuestion(questionDTO.getQuestion());
         question.setDescription(questionDTO.getDescription());
         question.setTopic(questionDTO.getTopic());
         question.setApproved(questionDTO.isApprove());

         User user = userRepository.findById(questionDTO.getUser_id())
                 .orElseThrow(() -> new RuntimeException("Error: user is not found."));
         question.setUser(user);

         return question;
     }
    
     /*
     * @Author : Rahul Chilampande
     * Created Date : 25-08-2022
     * Modified Date : 30-08-2022
     * Description : Returning All Deleted Question
     * Params : question_id(Long)
     * Return Type : String
     */

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

     /*
     * @Author : Rahul Chilampande
     * Created Date : 25-08-2022
     * Modified Date : 30-08-2022
     * Description : Set Answer User To Null
     * Params : Answer
     */
     private void setAnswerUserToNull(Answer answer) {
         this.answerService.deleteAnswer(answer.getAnswer_id());
     }

     /*
     * @Author : Rahul Chilampande
     * Created Date : 25-08-2022
     * Modified Date : 30-08-2022
     * Description : Mapping User To DTO
     * Params : User 
     * Return Type : UserDTO
     */
    private UserDTO mapUserToDto(User user) {
        UserDTO userDTO = new UserDTO();

        userDTO.setUser_id(user.getUser_id());
        userDTO.setFirstName(user.getFirstName());
        userDTO.setLastName(user.getLastName());
        userDTO.setUsername(user.getUsername());
        userDTO.setEmail(user.getEmail());
        userDTO.setPhone(user.getPhone());
        userDTO.setPassword(user.getPassword());

        Set<String> userRoles = new HashSet<>();

        Set<Role> userSet = user.getRoles();
        userSet.forEach(role -> {
            ERoles r = role.getRole();
            if (r == ERoles.ROLE_ADMIN) {
                userRoles.add("ROLE_ADMIN");
            } else {
                userRoles.add("ROLE_USER");
            }
        });

        userDTO.setRoles(userRoles);

        return userDTO;

    }

}
