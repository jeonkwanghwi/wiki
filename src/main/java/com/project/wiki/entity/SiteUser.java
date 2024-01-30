package com.project.wiki.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class SiteUser {

    /**
     * Spring Security에 User 클래스가 있어서 User 그대로 못쓰고
     * Member라는 건 Mysql에 있는데 그럼 대체 뭐라고 네이밍 해야함 ...??? 24.01.29. -광휘
     */

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String username; // 사용자 실명
    private String password;

    @Column(unique = true)
    private String nickname; // 닉네임 중복 방지

    /** unique = true 라고 해주면 UK로 지정해 유일한 값만 저장할 수 있어서 동일한 값이 저장되는 것을 막을 수 있다. ★★★★★  */
    @Column(unique = true)
    private String email;
}
