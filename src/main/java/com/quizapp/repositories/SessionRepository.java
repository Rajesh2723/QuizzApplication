package com.quizapp.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.quizapp.models.Session;

public interface SessionRepository extends JpaRepository<Session, Long>{
	Session findByid(Long id);
}
