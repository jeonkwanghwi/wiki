package com.project.wiki.repository;

import com.project.wiki.entity.Question;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Question 엔티티로 레포지터리를 생성하고, Question 엔티티의 PK가 Integer임을 설정
 */

public interface QuestionRepository extends JpaRepository<Question, Integer> {
}
