package com.doconnect.doconnectservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.doconnect.doconnectservice.entity.Answer;


@Repository
public interface AnswerRepository extends JpaRepository<Answer, Long>{

}
