package com.quizapp.services;

import org.springframework.stereotype.Service;

import com.quizapp.models.Quiz;
import com.quizapp.repositories.QuizRepository;

@Service
public class QuizServiceImpl implements QuizService{
	
	
	private QuizRepository quizRepository;
	
	
	public QuizServiceImpl(QuizRepository quizRepository) {
		super();
		this.quizRepository = quizRepository;
	}


	public Quiz saveQuiz(Quiz quiz) {
		return quizRepository.save(quiz);
	}
	
}
