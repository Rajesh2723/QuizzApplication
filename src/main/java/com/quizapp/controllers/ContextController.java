package com.quizapp.controllers;

import java.util.List;

import com.quizapp.models.Admin;
import com.quizapp.models.Question;
import com.quizapp.models.Quiz;
import com.quizapp.models.Session;
import com.quizapp.models.Student;
import com.quizapp.models.Teacher;

public class ContextController {

	public static Student student;
	public static Admin admin;
	public static Teacher teacher;
	public static Session session;
	public static Quiz quiz;
	public static List<Question> questions;
	
	public static Admin getAdmin() {
		return admin;
	}
	public static void setAdmin(Admin admin) {
		ContextController.admin = admin;
	}
	public static Student getStudent() {
		return student;
	}
	public static void setStudent(Student student) {
		ContextController.student = student;
	}
	public static Session getSession() {
		return session;
	}
	public static void setSession(Session session) {
		ContextController.session = session;
	}
	public static Quiz getQuiz() {
		return quiz;
	}
	public static void setQuiz(Quiz quiz) {
		ContextController.quiz = quiz;
	}
	public static Teacher getTeacher() {
		return teacher;
	}
	public static void setTeacher(Teacher teacher) {
		ContextController.teacher = teacher;
	}
	
	
}
