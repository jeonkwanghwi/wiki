package com.project.wiki.service;

import com.project.wiki.entity.SiteUser;
import com.project.wiki.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public SiteUser create(String username, String nickname, String email, String password) {
        SiteUser user = new SiteUser().builder()
                .username(username)
                .nickname(nickname)
                .email(email)
                .password(passwordEncoder.encode(password)) // 패스워드 암호화
                .build();
        this.userRepository.save(user);
        return user;
    }
}