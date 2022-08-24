package com.doconnect.doconnectservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.doconnect.doconnectservice.entity.Question;

public interface QuestionRepository extends JpaRepository<Question, Long>{

}
