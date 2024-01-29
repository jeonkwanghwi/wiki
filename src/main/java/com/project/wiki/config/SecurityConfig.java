package com.project.wiki.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

/**
 * @Configuration : 이 파일이 스프링의 환경 설정 파일임을 의미함. 여기선 스프링 시큐리티를 설정하기 위해 사용
 * @EnableWebSecurity : spring security를 활성화하여 모든 request가 스프링 시큐리티의 제어를 받도록 만들어줌.
 * 내부적으로 SecurityFilterChain 클래스가 동작하여 모든 요청 URL에 이 클래스가 필터로 적용되어 URL별로 특별한 설정을 할 수 있게 된다.
 * 스프링 시큐리티의 세부 설정은 @Bean 애너테이션을 통해 SecurityFilterChain 빈을 생성하여 설정할 수 있다.
 */
@Configuration
@EnableWebSecurity
public class SecurityConfig {

    /**
     * @Bean : 스프링에 의해 생성 또는 관리되는 객체를 의미함. controller, service, repository 모두 Bean임.
     * 이런식으로 직접 Bean으로 정의해 등록할 수도 있음.
     */
    @Bean
    SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests((authorizeHttpRequests) -> authorizeHttpRequests
                        .requestMatchers(new AntPathRequestMatcher("/**")).permitAll());
        /**
         * 로그인하지 않아도 즉, 인증 없이도 페이지 접근을 허용하는 것임. (permitAll)
         */
        return http.build();
    }
}