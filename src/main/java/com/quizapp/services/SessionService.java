package com.quizapp.services;

import java.util.List;

import com.quizapp.models.Question;
import com.quizapp.models.Session;

public interface SessionService{
	List<Session> getAllSessions();
	Session saveSession(Session session);
	Session findSessionById(Long id);
	
}
