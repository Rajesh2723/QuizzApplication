package com.quizapp.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.quizapp.models.Student;

public interface StudentRepository extends JpaRepository<Student, Long>{

	Student findByusername(String username);
	void deleteById(Long Id);
}
