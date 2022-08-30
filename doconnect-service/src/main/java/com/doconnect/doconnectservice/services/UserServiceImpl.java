package com.doconnect.doconnectservice.services;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
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

/*
 * @Author : Darshan Khairnar
 * Created Date : 25-8-2022
 * Modified Date : 28-8-2022
 * Description : Service Layer Implementation for User
 */

@Service
public class UserServiceImpl implements UserService {

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

    @Autowired
    EmailSenderService emailSenderService;

    @Autowired
    PasswordEncoder passwordEncoder;

    /*
     * @Author : Darshan Khairnar
     * Created Date : 25-8-2022
     * Modified Date : 28-8-2022
     * Description : Returning all users
     * Params : None
     * Return Type : List<UserDTO>
     */
    public List<UserDTO> getAllUsers() {

        List<User> userList = this.userRepository.findAll();

        return userList.stream().map(this::mapUserToDto).collect(Collectors.toList());

    }

    /*
     * @Author : Darshan Khairnar
     * Created Date : 25-8-2022
     * Modified Date : 28-8-2022
     * Description : Getting all admins
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
     * @Author : Darshan Khairnar
     * Created Date : 25-8-2022
     * Modified Date : 27-8-2022
     * Description : Registeration of User
     * Params : UserDTO
     * Return Type : string
     */
    public String registerUser(@Valid UserDTO userDTO) {

        User user = mapDtoTOUser(userDTO);
        this.userRepository.save(user);

        String body = "You are Successfylly Registered, To Login in your account go to this link\n"
                + "http://localhost:4200/login";
        this.emailSenderService.sendMail(userDTO.getEmail(), body, "Regeristeration is Successful");

        return "User Registered Successfully";
    }

    /*
     * @Author : Darshan Khairnar
     * Created Date : 25-8-2022
     * Modified Date : 27-8-2022
     * Description : Getting Patricular User with some user Id
     * Params : User id (Long)
     * Return Type : UserDTO
     */
    public UserDTO getUser(Long user_id) {

        User user = this.userRepository.findById(user_id)
                .orElseThrow(() -> new RuntimeException("Error : User is not found"));
        return mapUserToDto(user);
    }

    /*
     * @Author : Darshan Khairnar
     * Created Date : 25-8-2022
     * Modified Date : 27-8-2022
     * Description : Update User details
     * Params : UserDTO,user_id(long)
     * Return Type : String
     */
    public String updateUser(@Valid UserDTO userDTO, @Valid Long user_id) {

        User user = this.userRepository.findById(user_id)
                .orElseThrow(() -> new RuntimeException("Error : User is not found"));

        user.setFirstName(userDTO.getFirstName());
        user.setLastName(userDTO.getLastName());
        user.setUsername(userDTO.getUsername());
        user.setEmail(userDTO.getEmail());
        user.setPhone(userDTO.getPhone());
        user.setPassword(passwordEncoder.encode(userDTO.getPassword()));

        Set<String> strRoles = userDTO.getRoles();
        Set<Role> roles = new HashSet<>();

        if (strRoles == null) {
            Role userRole = roleRespository.findByRole(ERoles.ROLE_USER)
                    .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
            roles.add(userRole);
        }

        else {
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

        String body = "Your profile is Updated by Admin " + "\n" +
                "Your details are as below," + "\n" +
                "Username  : " + userDTO.getUsername() + "\n" +
                "Password : " + userDTO.getPassword();

        this.emailSenderService.sendMail(userDTO.getEmail(), body, "Your Profile is Updated");

        return "User Updated Successfully";

    }

    /*
     * @Author : Darshan Khairnar
     * Created Date : 25-8-2022
     * Modified Date : 28-8-2022
     * Description : Getting all admins
     * Params : user_id(long)
     * Return Type : String
     */
    public String deleteUser(Long user_id) {
        User user = this.userRepository.findById(user_id)
                .orElseThrow(() -> new RuntimeException("Error : User is not found"));
        user.setRoles(new HashSet<>());
        List<Question> questions = this.questionRepository.findAllByUser(user)
                .orElseThrow(() -> new RuntimeException("Error : Question is not found"));

        System.out.println(questions);
        List<Answer> answers = this.answerRepository.findAllByUser(user)
                .orElseThrow(() -> new RuntimeException("Error : Answer is not found"));

        questions.forEach(this::setQuestionUser);
        answers.forEach(this::setAnswerUser);

        this.userRepository.deleteById(user_id);
        return "User Deleted Successfully";
    }

    /*
     * @Author : Darshan Khairnar
     * Created Date : 25-8-2022
     * Modified Date : 28-8-2022
     * Description : Getting user by username
     * Params : username(string)
     * Return Type : UserDTO
     */
    @Override
    public UserDTO getUserByUsername(String username) {
        User user = this.userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("Error : User is not found"));
        return this.mapUserToDto(user);
    }

    /*
     * @Author : Darshan Khairnar
     * Created Date : 25-8-2022
     * Modified Date : 27-8-2022
     * Description : Mapping UserDTO to User
     * Params : UserDTO
     * Return Type : User
     */
    private User mapDtoTOUser(UserDTO userDTO) {
        User user = new User();
        user.setFirstName(userDTO.getFirstName());
        user.setLastName(userDTO.getLastName());
        user.setUsername(userDTO.getUsername());
        user.setEmail(userDTO.getEmail());
        user.setPhone(userDTO.getPhone());
        user.setPassword(passwordEncoder.encode(userDTO.getPassword()));

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

    /*
     * @Author : Darshan Khairnar
     * Created Date : 25-8-2022
     * Modified Date : 27-8-2022
     * Description : Mapping User to UserDTO
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

    private void setQuestionUser(Question question) {
        this.questionService.deleteQuestion(question.getQuestion_id());
    }

    private void setAnswerUser(Answer answer) {
        this.answerService.deleteAnswer(answer.getAnswer_id());
    }
}
