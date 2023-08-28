package com.quizapp.models;

import java.beans.Transient;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "Quizes")
public class Quiz {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long quizId;
	
	@Column(name = "subject")
	private String subject;
	
	@OneToMany(targetEntity=Question.class, mappedBy = "quiz", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	private List<Question> questions;
	
	@OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "quiz")
	private Session session;
	
	
	public Quiz() {
		
	}
	
	public Quiz(String subject, List<Question> questions, Session session) {
		super();
		this.subject = subject;
		this.questions = questions;
		this.session = session;
	}

	public Long getQuizId() {
		return quizId;
	}

	public void setQuizId(Long quizId) {
		this.quizId = quizId;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public List<Question> getQuestions() {
		return questions;
	}

	public void setQuestions(List<Question> questions) {
		this.questions = questions;
	}

	public Session getSession() {
		return session;
	}

	public void setSession(Session session) {
		this.session = session;
	}
	
	
}
