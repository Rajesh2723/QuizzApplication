package com.quizapp.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.quizapp.models.Quiz;

public interface QuizRepository extends JpaRepository<Quiz, Long>{
	
}
