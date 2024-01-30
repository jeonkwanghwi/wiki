package com.project.wiki.config;

import lombok.Getter;


/** 값을 변경할 필요가 없어서 setter는 없음
 * 나중에 관리자(ADMIN)가 다른사람의 게시물 삭제하는 기능 넣너놓기. -24.01.30. 광휘- */
@Getter
public enum UserRole {
    ADMIN("ROLE_ADMIN"),
    USER("ROLE_USER");
    UserRole(String value) {
        this.value = value;
    }
    private String value;
}