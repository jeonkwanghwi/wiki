package com.project.wiki.service;

import java.security.Principal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.project.wiki.entity.SiteUser;
import com.project.wiki.exception.DataNotFoundException;
import com.project.wiki.entity.Question;
import com.project.wiki.repository.QuestionRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import org.springframework.web.server.ResponseStatusException;

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
    
    // 페이징 기능, 최신 글이 가장 위에 표시됨. (에타 게시글처럼 맨 위가 최신글 // 근데 수정시간 기준으로 최신 정렬되기도 함...)
    public Page<Question> getList(int page) {
        List<Sort.Order> sorts = new ArrayList<>();
        sorts.add(Sort.Order.desc("createDate")); // 최신순(=역순 = desc)으로 조회
        Pageable pageable = PageRequest.of(page, 10, Sort.by(sorts));
        return this.questionRepository.findAll(pageable);
        /**
         * 게시물을 최신순으로 조회하려면 이와 같이 PageRequest.of 메서드의 세 번째 매개변수에 Sort 객체를 전달해야 한다.
         * createDate를 역순(Desc)으로 조회하려면 Sort.Order.desc("createDate")와 같이 작성한다.
         */
    }

    // 게시글 생성
    public void create(String subject, String content, SiteUser user) {
        Question q = new Question().builder()
                .subject(subject)
                .content(content)
                .createDate(LocalDateTime.now())
                .author(user)
                .build();
        this.questionRepository.save(q);
    }
    
    // 수정
    public void modify(Question question, String subject, String content) {
        question.updateInfo(subject, content, LocalDateTime.now());
        this.questionRepository.save(question);
    }
    
    // 삭제
    public void delete(Question question) {
        this.questionRepository.delete(question);
    }
}