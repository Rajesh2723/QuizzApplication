package com.quizapp.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.quizapp.models.Option;
public interface OptionRepository extends JpaRepository<Option, Long> {

}
