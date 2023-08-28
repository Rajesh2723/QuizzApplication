package com.quizapp.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.quizapp.models.Student;
import com.quizapp.models.Teacher;
import com.quizapp.repositories.TeacherRepository;

@Service
public class TeacherServiceImpl implements TeacherService{

	
	private TeacherRepository teacherRepository;

	public TeacherServiceImpl(TeacherRepository teacherRepository) {
		super();
		this.teacherRepository = teacherRepository;
	}

	@Override
	public List<Teacher> getAllTeachers() {
		return teacherRepository.findAll();
	}

	@Override
	public Teacher saveTeacher(Teacher teacher) {
		return teacherRepository.save(teacher);
	}

	@Override
	public Teacher getTeacherById(Long id) {
		return teacherRepository.findById(id).get();
	}

	@Override
	public Teacher updateTeacher(Teacher teacher) {
		return teacherRepository.save(teacher);
	}

	@Override
	public void deleteTeacherById(Long Id) {
		teacherRepository.deleteById(Id);
	}

	@Override
	public Teacher findTeacherByUsername(String username) {
		return teacherRepository.findByusername(username);
	}
	
}
