package com.project.wiki.controller;

import com.project.wiki.entity.Question;
import com.project.wiki.repository.QuestionRepository;
import com.project.wiki.service.QuestionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@RequiredArgsConstructor // final 붙은 속성을 포함하는 생성자를 자동으로 만들어 줌
@Controller
@RequestMapping("/question")
public class QuestionController {

    private final QuestionService questionService;
    /**
     * Model 객체는 class와 템플릿 사이의 연결 고리 역할을 한다.
     * Model 객체에 값을 담아 두면 템플릿에서 그 값을 사용할 수 있다.
     * Model 객체는 따로 생성할 필요 없이 컨트롤러의 메서드에 매개변수로 지정하기만 하면 스프링 부트가 자동으로 Model 객체를 생성한다.
     */
    @GetMapping("/list")
    public String list(Model model) {
        List<Question> questionList = this.questionService.getList();

        // QuestionController의 list 메서드에서 조회한 질문 목록 데이터를 ‘questionList’라는 이름으로 Model 객체에 저장
        model.addAttribute("questionList", questionList);
        return "question_list";
    }
    @GetMapping("/detail/{id}")
    public String detail(Model model, @PathVariable("id") Integer id) {
        Question question = this.questionService.getQuestion(id);
        model.addAttribute("question", question);
        return "question_detail";
    }

    @GetMapping("/create")
    public String questionCreate() {
        return "question_form";
    }
}