package com.quizapp.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.quizapp.models.Question;
import com.quizapp.models.Session;
import com.quizapp.repositories.SessionRepository;

@Service
public class SessionServiceImpl implements SessionService{
	
	
	private SessionRepository sessionRepository;

	public SessionServiceImpl(SessionRepository sessionRepository) {
		super();
		this.sessionRepository = sessionRepository;
	}

	@Override
	public List<Session> getAllSessions() {
		return sessionRepository.findAll();
	}

	@Override
	public Session saveSession(Session session) {
		return sessionRepository.save(session);
	}

	@Override
	public Session findSessionById(Long id) {
		return sessionRepository.findByid(id);
	}

	
}
