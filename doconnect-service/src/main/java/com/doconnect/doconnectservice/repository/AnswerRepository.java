package com.doconnect.doconnectservice.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.doconnect.doconnectservice.entity.Answer;
import com.doconnect.doconnectservice.entity.Question;
import com.doconnect.doconnectservice.entity.User;


@Repository
public interface AnswerRepository extends JpaRepository<Answer, Long>{

    Optional<List<Answer>> findAllByUser(User user);
    Optional<List<Answer>> findAllByQuestion(Question question);
    Optional<List<Answer>> findAllByIsApproved(boolean value);
}
