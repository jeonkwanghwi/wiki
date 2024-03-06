package com.project.wiki.repository;

import com.project.wiki.entity.Question;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * Question 엔티티로 레포지터리를 생성하고, Question 엔티티의 PK가 Integer임을 설정
 */

public interface QuestionRepository extends JpaRepository<Question, Integer> {
    Question findBySubject(String subject);
    Question findBySubjectAndContent(String subject, String content);
    List<Question> findBySubjectLike(String subject);
    Page<Question> findAll(Pageable pageable); // 페이징 처리
    Page<Question> findAll(Specification<Question> spec, Pageable pageable); // Question 엔티티에서 질문 조회, 페이징까지해서 반환

    @Query("SELECT q FROM Question q WHERE SIZE(q.voter) >= 5")
    List<Question> findHotQuestions();
}
