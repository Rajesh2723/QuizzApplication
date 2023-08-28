package com.quizapp;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.quizapp.models.Question;
import com.quizapp.repositories.QuestionRepository;

@SpringBootApplication
public class QuizzApplication {

	public static void main(String[] args) {
		SpringApplication.run(QuizzApplication.class, args);
	}
}
