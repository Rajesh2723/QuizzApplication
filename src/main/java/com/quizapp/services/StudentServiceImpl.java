package com.quizapp.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.quizapp.models.Student;
import com.quizapp.repositories.StudentRepository;

@Service
public class StudentServiceImpl implements StudentService {

	private StudentRepository studentRepository;

	public StudentServiceImpl(StudentRepository studentRepository) {
		super();
		this.studentRepository = studentRepository;
	}

	@Override
	public List<Student> getAllStudents() {
		return studentRepository.findAll();
	}

	@Override
	public Student saveStudent(Student student) {
		return studentRepository.save(student);
	}

	
	public Student findStudentByUsernameAndPassword(String username){
	        Student student = studentRepository.findByusername(username);
	        return student; 
	 }

	@Override
	public Student getStudentById(Long id) {
		return studentRepository.findById(id).get();
	}

	@Override
	public Student updateStudent(Student student) {
		return studentRepository.save(student);
	}

	@Override
	public void deleteStudentById(Long Id){
		 studentRepository.deleteById(Id);
	}
}
