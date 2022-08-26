package com.doconnect.doconnectservice.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.doconnect.doconnectservice.entity.Question;
import com.doconnect.doconnectservice.entity.User;

@Repository
public interface QuestionRepository extends JpaRepository<Question, Long> {

  Optional<Question> findById(Long question_id);
  Optional<List<Question>> findAllByUser(User user);
  Optional<List<Question>> findAllByIsApproved(boolean value);

}
