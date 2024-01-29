package com.project.wiki.controller;

import com.project.wiki.entity.AnswerForm;
import com.project.wiki.entity.Question;
import com.project.wiki.entity.QuestionForm;
import com.project.wiki.repository.QuestionRepository;
import com.project.wiki.service.QuestionService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

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
    public String list(Model model, @RequestParam(value="page", defaultValue="0") int page) {
        Page<Question> paging = this.questionService.getList(page);
        model.addAttribute("paging", paging);
        return "question_list";
    }

    @GetMapping("/detail/{id}")
    public String detail(Model model, @PathVariable("id") Integer id, AnswerForm answerForm) {
        Question question = this.questionService.getQuestion(id);
        model.addAttribute("question", question);
        return "question_detail";
    }


    /**
     * 한 클래스에서 동일한 메서드명을 사용 : 메소드 오버로딩
     * 매개변수 형태 다르게 해서 같은 이름으로 사용 가능.
     */
    @GetMapping("/create")
    public String questionCreate(QuestionForm questionForm) {
        return "question_form";
    }

    @PostMapping("/create")
    public String questionCreate(@Valid QuestionForm questionForm, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "question_form";
        }
        this.questionService.create(questionForm.getSubject(), questionForm.getContent());
        return "redirect:/question/list";
    }
}