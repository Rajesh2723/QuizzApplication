package com.quizapp.controllers;

import java.sql.Timestamp;
import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.quizapp.dto.AnswersDTO;
import com.quizapp.models.Question;
import com.quizapp.models.Quiz;
import com.quizapp.models.Session;
import com.quizapp.services.QuestionService;
import com.quizapp.services.SessionService;
import com.quizapp.utils.Utils;

@Controller
@RequestMapping("/quiz")
public class QuizController {
	
	private QuestionService questionService;
	
	@Autowired
	private SessionService sessionService;
	

	public QuizController(QuestionService questionService) {
		super();
		this.questionService = questionService;
	}

	@GetMapping("/{subject}")
	public String showQuiz(@PathVariable String subject, Model model) {
		Session session = new Session();
		session.setStart_time(new Timestamp(System.currentTimeMillis()));
		List<Question> questions = questionService.getAllQuestionsBySubject(subject);
		Quiz quiz = new Quiz(subject, questions, session);
		session.setQuiz(quiz);
		session.setScore(0);
		ContextController.getStudent().setSession(session);
		session.setStudentId(ContextController.getStudent().getId());
		session.setStudentName(ContextController.getStudent().getUsername());
		ContextController.setSession(session); //optional shared context
		sessionService.saveSession(session);
		ContextController.questions = questions;
		model.addAttribute("questions", questions);
		AnswersDTO answersDto = new AnswersDTO();
		model.addAttribute("answersDto", answersDto);
		model.addAttribute("student", ContextController.getStudent());
		return "quiz";
	}
	
//	@PostMapping("/{subject}/submit")
//	public String validateQuiz(@PathVariable String subject, @ModelAttribute AnswersDTO answersDto,
//			Model model) {
//		List<Question> questions = questionService.getAllQuestionsBySubject(subject);
//		int correct=0;
//		int opted=0;
//		String[] submittedAnswers = answersDto.getAnswers();
//		for(int i=0; i<questions.size(); i++) {
//			if(submittedAnswers[i] != null) {
//				opted++;
//				if(submittedAnswers[i].equalsIgnoreCase(questions.get(i).getOptionCorrect())) {
//					correct++;
//				}
//			}
//		}
//		double score = Utils.calculateNegativeMarks(opted, correct);
//		Session existingSession = sessionService.findSessionById(ContextController
//				.getSession().getId());
//		existingSession.setEnd_time(new Timestamp(System.currentTimeMillis()));
//		existingSession.setScore(score);
//		sessionService.saveSession(existingSession);
//		System.out.println(score);
//		
//		model.addAttribute("score", score);
//		model.addAttribute("total", "Out of " + questions.size());
//		model.addAttribute("student", ContextController.getStudent());
//		return "quiz-results";
//	}
	
	
	@PostMapping("/{subject}/submit")
	public String validateQuiz(@PathVariable String subject, 
			@ModelAttribute AnswersDTO answersDto,
			Model model) {
		List<Question> questions = ContextController.questions;
		int correct=0;
		int opted=0;
		String[] submittedAnswers = answersDto.getAnswers();
		for(int i=0; i<questions.size(); i++) {
			if(submittedAnswers[i] != null) {
				opted++;
				if(submittedAnswers[i].equalsIgnoreCase(questions.get(i).getOptionCorrect())) {
					correct++;
				}
			}
		}
		double score = Utils.calculateNegativeMarks(opted, correct);
		Session existingSession = sessionService.findSessionById(ContextController
				.getSession().getId());
		existingSession.setEnd_time(new Timestamp(System.currentTimeMillis()));
		existingSession.setScore(score);
		sessionService.saveSession(existingSession);
		System.out.println(score);
		
		model.addAttribute("score", score);
		model.addAttribute("total", "Out of " + questions.size());
		model.addAttribute("student", ContextController.getStudent());
		return "quiz-results";
	}
	
}
