package com.quizapp.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import com.quizapp.models.Teacher;

public interface TeacherRepository extends JpaRepository<Teacher, Long>{

	void deleteById(Long Id);
	Teacher findByusername(String username);
}
