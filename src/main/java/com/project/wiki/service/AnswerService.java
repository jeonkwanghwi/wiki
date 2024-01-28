package com.project.wiki.service;

import com.project.wiki.entity.Answer;
import com.project.wiki.entity.Question;
import com.project.wiki.repository.AnswerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@RequiredArgsConstructor
@Service
public class AnswerService {
    private final AnswerRepository answerRepository;

    public void create(Question question, String content) {
        Answer answer = new Answer().builder()
                .content(content)
                .createDate(LocalDateTime.now())
                .question(question)
                .build();
        this.answerRepository.save(answer);
    }
}