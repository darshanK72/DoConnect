package com.doconnect.doconnectservice.services;


import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.doconnect.doconnectservice.dto.UserDTO;
import com.doconnect.doconnectservice.entity.Answer;
import com.doconnect.doconnectservice.entity.Question;
import com.doconnect.doconnectservice.entity.Role;
import com.doconnect.doconnectservice.entity.User;
import com.doconnect.doconnectservice.enums.ERoles;
import com.doconnect.doconnectservice.repository.AnswerRepository;
import com.doconnect.doconnectservice.repository.QuestionRepository;
import com.doconnect.doconnectservice.repository.RoleRespository;
import com.doconnect.doconnectservice.repository.UserRepository;


@Service
public class UserServiceImpl implements UserService{

    @Autowired
    UserRepository userRepository;

    @Autowired
    RoleRespository roleRespository;

    @Autowired
    QuestionRepository questionRepository;

    @Autowired
    AnswerRepository answerRepository;

    @Autowired
    QuestionServiceImpl questionService;

    @Autowired
    AnswerServiceImpl answerService;

    public List<UserDTO> getAllUsers() {

        List<User> userList = this.userRepository.findAll();

        return userList.stream().map(this::mapUserToDto).collect(Collectors.toList());
        
    }

    public String registerUser(@Valid UserDTO userDTO) {

        User user = mapDtoTOUser(userDTO);
        this.userRepository.save(user);
        return "User Registered Successfully";
    }

    private User mapDtoTOUser(UserDTO userDTO)
    {
        User user = new User();
        user.setFirstName(userDTO.getFirstName());
        user.setLastName(userDTO.getLastName());
        user.setUsername(userDTO.getUsername());
        user.setEmail(userDTO.getEmail());
        user.setPhone(userDTO.getPhone());
        user.setPassword(userDTO.getPassword());

        Set<String> strRoles = userDTO.getRoles();
        Set<Role> roles = new HashSet<>();
    
        if (strRoles == null) {
          Role userRole = roleRespository.findByRole(ERoles.ROLE_USER)
              .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
          roles.add(userRole);
        } else {
          strRoles.forEach(role -> {
            switch (role) {
            case "user":
                Role userRole = roleRespository.findByRole(ERoles.ROLE_USER)
                      .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                roles.add(userRole);
                break;

            case "admin":
              Role adminRole = roleRespository.findByRole(ERoles.ROLE_ADMIN)
                  .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
              roles.add(adminRole);
              break;
            }
          });
        }
        user.setRoles(roles);

        return user;
    }

    private UserDTO mapUserToDto(User user)
    {
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
        userSet.forEach(role ->{
            ERoles r = role.getRole();
            if(r == ERoles.ROLE_ADMIN)
            {
                userRoles.add("ROLE_ADMIN");
            }
            else
            {
                userRoles.add("ROLE_USER");
            }
        });

        userDTO.setRoles(userRoles);

        return userDTO;

    }

    public UserDTO getUser(Long user_id) {

        User user = this.userRepository.findById(user_id).orElseThrow(() -> new RuntimeException("Error : User is not found"));
        return mapUserToDto(user);
    }

    public String updateUser(@Valid UserDTO userDTO, @Valid Long user_id) {

        User user = this.userRepository.findById(user_id).orElseThrow(() -> new RuntimeException("Error : User is not found"));

        user.setFirstName(userDTO.getFirstName());
        user.setLastName(userDTO.getLastName());
        user.setUsername(userDTO.getUsername());
        user.setEmail(userDTO.getEmail());
        user.setPhone(userDTO.getPhone());
        user.setPassword(userDTO.getPassword());

        Set<String> strRoles = userDTO.getRoles();
        Set<Role> roles = new HashSet<>();

        if (strRoles == null) 
        {
            Role userRole = roleRespository.findByRole(ERoles.ROLE_USER)
                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
            roles.add(userRole);
        }

        else 
        {
            strRoles.forEach(role -> {
              switch (role) {
              case "user":
                  Role userRole = roleRespository.findByRole(ERoles.ROLE_USER)
                        .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                  roles.add(userRole);
                  break;
  
              case "admin":
                Role adminRole = roleRespository.findByRole(ERoles.ROLE_ADMIN)
                    .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                roles.add(adminRole);
                break;
              
              }
            });
          }

          user.setRoles(roles);

        this.userRepository.save(user);
        
        return "User Updated Successfully";
        
    }

    public String deleteUser(Long user_id) {
        User user = this.userRepository.findById(user_id).orElseThrow(() -> new RuntimeException("Error : User is not found"));
        user.setRoles(new HashSet<>());
        List<Question> questions = this.questionRepository.findAllByUser(user).orElseThrow(() -> new RuntimeException("Error : Question is not found"));

        System.out.println(questions);
        List<Answer> answers = this.answerRepository.findAllByUser(user).orElseThrow(() -> new RuntimeException("Error : Answer is not found"));

        questions.forEach(this::setQuestionUser);
        answers.forEach(this::setAnswerUser);

        this.userRepository.deleteById(user_id);
        return "User Deleted Successfully";
    }

    private void setQuestionUser(Question question)
    {
        this.questionService.deleteQuestion(question.getQuestion_id());
    }
    private void setAnswerUser(Answer answer)
    {
        this.answerService.deleteAnswer(answer.getAnswer_id());
    }
}
