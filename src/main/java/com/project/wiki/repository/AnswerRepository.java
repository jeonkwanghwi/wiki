package com.project.wiki.repository;

import com.project.wiki.entity.Answer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AnswerRepository extends JpaRepository<Answer, Integer> {
}
