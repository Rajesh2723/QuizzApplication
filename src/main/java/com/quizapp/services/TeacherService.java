package com.quizapp.services;

import java.util.List;

import com.quizapp.models.Student;
import com.quizapp.models.Teacher;

public interface TeacherService {
	List<Teacher> getAllTeachers();
	Teacher findTeacherByUsername(String username);
	Teacher saveTeacher(Teacher teacher);
	Teacher getTeacherById(Long id);
	Teacher updateTeacher(Teacher teacher);
	void deleteTeacherById(Long Id);
}
