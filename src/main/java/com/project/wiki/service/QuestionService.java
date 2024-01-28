package com.project.wiki.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import com.project.wiki.exception.DataNotFoundException;
import com.project.wiki.entity.Question;
import com.project.wiki.repository.QuestionRepository;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class QuestionService {

    private final QuestionRepository questionRepository;

    // 질문 리스트 가져오기
    public List<Question> getList() {
        return this.questionRepository.findAll();
    }

    // 질문 보여주기
    public Question getQuestion(Integer id) {
        Optional<Question> question = this.questionRepository.findById(id);
        if (question.isPresent()) {
            return question.get();
        } else {
            throw new DataNotFoundException("question not found");
        }
    }

    // 질문으로 저장하기
    public void create(String subject, String content) {
        Question q = new Question().builder()
                .subject(subject)
                .content(content)
                .createDate(LocalDateTime.now())
                .build();
        this.questionRepository.save(q);
    }
}