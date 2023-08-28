package com.quizapp.services;

import java.util.List;

import com.quizapp.models.Question;
import com.quizapp.models.Student;

public interface QuestionService {
	List<Question> getAllQuestionsBySubject(String subject);
	Question saveQuestion(Question question);
	Question findQuestionByquestionId(Long id);
	void deleteQuestionByquestionId(Long id);
}
