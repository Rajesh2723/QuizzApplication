package com.quizapp.services;

import java.util.List;

import com.quizapp.models.Student;

public interface StudentService {
	List<Student> getAllStudents();
	Student saveStudent(Student student);
	Student getStudentById(Long id);
	Student updateStudent(Student student);
	void deleteStudentById(Long Id);
	
}
