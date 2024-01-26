package com.project.wiki.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class MainController {

    @GetMapping("/sbb")
    @ResponseBody
    public String index() {
        return "안녕하세요 sbb에 오신것을 환영합니다.";
    }

    // 메인페이지가 redirect 시켜주는 페이지로(/question/list) 설정됨.
    @GetMapping("/")
    public String root() {
        return "redirect:/question/list"; // http://localhost:8080 에 접속하면 바로 리다이렉트
    }
}