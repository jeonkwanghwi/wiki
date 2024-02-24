package com.project.wiki.repository;

import com.project.wiki.entity.Answer;
import com.project.wiki.entity.Question;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

public interface AnswerRepository extends JpaRepository<Answer, Integer> {

    Page<Answer> findAllByQuestion(Question question, Pageable pagealbe);


}
