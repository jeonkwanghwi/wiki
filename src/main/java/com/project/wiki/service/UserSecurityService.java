package com.project.wiki.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.project.wiki.config.UserRole;
import com.project.wiki.entity.SiteUser;
import com.project.wiki.repository.UserRepository;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class UserSecurityService implements UserDetailsService {

    /** UserSecurityService : spring security가 로그인시 사용하는데, UserDetailsService를 구현해야하는 서비스임.
     *   UserDetailsService : loadUserByUsername 메소드를 구현하도록 강제함.
     *   loadUserByUsername : 사용자명(username)으로 스프링 시큐리티의 사용자(User) 객체를 조회하여 리턴하는 메서드이다. */
    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<SiteUser> _siteUser = this.userRepository.findByusername(username);
        if (_siteUser.isEmpty()) {
            throw new UsernameNotFoundException("사용자를 찾을수 없습니다.");
        }
        SiteUser siteUser = _siteUser.get();
        List<GrantedAuthority> authorities = new ArrayList<>();

        /** 사용자의 role에 따라 다른 권한을 부여 */
        if ("admin".equals(username)) {
            authorities.add(new SimpleGrantedAuthority(UserRole.ADMIN.getValue()));
        } else {
            authorities.add(new SimpleGrantedAuthority(UserRole.USER.getValue()));
        }
        /** 여기서의 비밀번호가 DB와 일치하는지에 대한 기능은 security에 내장되어있음. */
        return new User(siteUser.getUsername(), siteUser.getPassword(), authorities);
    }
}
