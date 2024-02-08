package com.project.wiki.entity;

import java.time.LocalDateTime;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
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
@AllArgsConstructor
public class Answer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(columnDefinition = "TEXT")
    private String content;

    private LocalDateTime createDate; // 글 생성시간
    private LocalDateTime modifyDate; // 글 수정시간

    // 여러 답변 <-> 1개의 질문
    @ManyToOne
    private Question question;

    @ManyToOne
    private SiteUser author;

    public void updateInfo(String content, LocalDateTime modifyDate) {
        this.content = content;
        this.modifyDate = modifyDate;
    }
}