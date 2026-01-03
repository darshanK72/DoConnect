package com.doconnect.doconnectservice.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.doconnect.doconnectservice.entity.Question;
import com.doconnect.doconnectservice.entity.User;

/*
 * @Author : Rahul Chilampande
 * Created Date : 25-8-2022
 * Modified Date : 30-8-2022
 * Description : Question Repository Class
 */

@Repository
public interface QuestionRepository extends JpaRepository<Question, Long> {

  Optional<Question> findById(Long question_id);
  Optional<List<Question>> findAllByUser(User user);
  Optional<List<Question>> findAllByIsApproved(boolean value);
  Optional<List<Question>> findByQuestionContainingIgnoreCase(String query);
  Optional<List<Question>> findByDescriptionContainingIgnoreCase(String query);
}
