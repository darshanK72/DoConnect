package com.doconnect.doconnectservice;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import com.doconnect.doconnectservice.entity.Answer;
import com.doconnect.doconnectservice.entity.Question;
import com.doconnect.doconnectservice.entity.User;
import com.doconnect.doconnectservice.entity.Answer.AnswerBuilder;
import com.doconnect.doconnectservice.entity.Question.QuestionBuilder;
import com.doconnect.doconnectservice.entity.User.UserBuilder;
import com.doconnect.doconnectservice.repository.AnswerRepository;
import com.doconnect.doconnectservice.repository.QuestionRepository;

import com.doconnect.doconnectservice.repository.UserRepository;

@SpringBootTest
class DoconnectServiceApplicationTests {

	@Autowired
	AnswerRepository answerRepository;

	@Autowired
	QuestionRepository questionRepository;

	@Autowired
	UserRepository userRepository;
	/*@Test
	void contextLoads() {
	}*/

	@Test
	public void Question(){
		QuestionBuilder question = Question.builder()
				.question("what is java")
				.description("Represent working of java")
				.topic("Java")
				.isApproved(true);
	}

	@Test
	public void Answer(){
		AnswerBuilder answer = Answer.builder()
			.answer("java is a Object oriented programming language")
			.isApproved(true);
	}

	@Test
	public void User(){
		UserBuilder user = User.builder()
				.username("Samarthan")
				.password("samarth@09")
				.firstName("Samarthan")
				.lastName("Reddy")
				.email("samarth99@gmail.com")
				.phone("9856741239")
				.isEnabled(false);
	}


}


/*
 
   public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long role_id;

    @Enumerated(EnumType.STRING)
    @Size(max = 20)
    private ERoles role;


 */