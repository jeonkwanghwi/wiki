package com.project.wiki.entity;

import java.time.LocalDateTime;
import java.util.List;

import jakarta.persistence.*;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * 일부러 Setter 없이 한번 해보려고 Builder 패턴 적용!!
 * Setter는 쓰지 않는 것으로 진행해보자
 */
@Getter
@Builder
@Entity
@NoArgsConstructor
public class Question {
    @Id // pk로 지정
    @GeneratedValue(strategy = GenerationType.IDENTITY) // id값이 1씩 자동 증가
    private Integer id;

    /**
     엔티티의 속성은 @Column 애너테이션을 사용하지 않더라도 테이블의 열로 인식한다.
    **/
    @Column(length = 200) // 열의 세부 설정을 위해 설정해줌
    private String subject;

    @Column(columnDefinition = "TEXT") // 열의 세부 설정을 위해 설정해줌, TEXT로 설정하면 글자수 제한 X
    private String content;

    private LocalDateTime createDate;

    // 질문 1개에 답변은 여러개 가능
    @OneToMany(mappedBy = "question", cascade = CascadeType.REMOVE)
    private List<Answer> answerList;

    /**
     * 질문_1개, 답변_N개 그런데 보통 게시판 서비스에서는 질문을 삭제하면 그에 달린 답변들도 함께 삭제된다.
     * 질문을 삭제하면 그에 달린 답변들도 모두 삭제되도록 cascade = CascadeType.REMOVE를 사용했다.
     */
}