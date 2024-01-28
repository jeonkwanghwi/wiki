package com.project.wiki;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "entity not found")
public class DataNotFoundException extends RuntimeException {
    private static final long serialVersionUID = 1L;
    public DataNotFoundException(String message) {
        super(message);
    }

    /**
     * DataNotFoundException : DB에서 특정 엔티티 또는 데이터를 찾을 수 없을 때 발생
     * 예외 발생시, 설정된 HTTP 상태 코드(HttpStatus.NOT_FOUND)와 이유("entity not found")를 포함한 응답을 생성해  클라이언트에게 반환
     * RuntimeException은 실행 시 발생하는 예외라는 의미
     */
}