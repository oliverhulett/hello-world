package com.example.jpahibernatecrud.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.jpahibernatecrud.model.Question;

@Repository
public interface QuestionRepository extends JpaRepository<Question, Long> {
}
