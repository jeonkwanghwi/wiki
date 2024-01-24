package com.project.wiki;

import com.project.wiki.entity.Answer;
import com.project.wiki.entity.Question;
import com.project.wiki.repository.QuestionRepository;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
class WikiApplicationTests {

    @Autowired
    private QuestionRepository questionRepository;

    @Test
    // Transactional이 없어지니까 오히려 값이 들어간다 ?
    void testJpa() {
        Question q1 = new Question().builder()
                .subject("sbb가 무엇인가요 1")
                .content("sbb에 대해서 알고 싶습니다.1")
                .createDate(LocalDateTime.now())
                .build();
        questionRepository.save(q1); // 첫번째 질문 저장


        Question q2 = new Question().builder()
                .subject("sbb가 무엇인가요 2")
                .content("sbb에 대해서 알고 싶습니다.2")
                .createDate(LocalDateTime.now())
                .build();
        questionRepository.save(q2); // 두번째 질문 저장

    }
}
