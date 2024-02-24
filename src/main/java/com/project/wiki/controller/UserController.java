package com.project.wiki.controller;

import com.project.wiki.dto.UserCreateForm;
import com.project.wiki.entity.SiteUser;
import com.project.wiki.repository.UserRepository;
import com.project.wiki.service.UserService;
import jakarta.validation.Valid;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Optional;

@RequiredArgsConstructor
@Controller
@RequestMapping("/user")
public class UserController {

    private final UserService userService;
    private final UserRepository userRepository;


    @GetMapping("/signup")
    public String signup(UserCreateForm userCreateForm) {
        return "signup_form";
    }

    @PostMapping("/signup")
    public String signup(@Valid UserCreateForm userCreateForm, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "signup_form";
        }

        // 비밀번호 잘 썼는지 체크
        if (!userCreateForm.getPassword1().equals(userCreateForm.getPassword2())) {

            /** 매개변수 순서대로 필드명, 오류코드(사용자지정인듯?), 오류메세지 */
            bindingResult.rejectValue("password2", "passwordInCorrect", "패스워드가 일치하지 않습니다.");
            return "signup_form";
        }

        /** 중복회원 방지하기
         * 닉네임 또는 이메일이 하나라도 중복이라면 오류 */
        try {
            userService.create(userCreateForm.getUsername(), userCreateForm.getNickname(),
                    userCreateForm.getEmail(), userCreateForm.getPassword1());
        }catch(DataIntegrityViolationException e) {
            e.printStackTrace();
            bindingResult.reject("signupFailed", "이미 등록된 사용자입니다.");
            return "signup_form";
        }catch(Exception e) {
            e.printStackTrace();
            bindingResult.reject("signupFailed", e.getMessage());
            return "signup_form";
        }

        return "redirect:/";
    }

    /** login 기능 */
    @GetMapping("/login")
    public String login() {
        return "login_form";
    }

    /** 비밀번호 찾기 기능 */
    @GetMapping("/findPW")
    public String findPW() {
        return "findPW_form";
    }

    /** 패치노트 */
    @GetMapping("/patchNote")
    public String patchNote() {
        return "patchnote_form";
    }

    @PostMapping("/findPW")
    public String findPassword(@RequestParam("nickname") String nickname, Model model) {
        Optional<SiteUser> userOptional = userRepository.findByNickname(nickname);
        if (userOptional.isPresent()) {
            SiteUser user = userOptional.get();
            model.addAttribute("password", user.getPassword());
        } else {
            model.addAttribute("error", "해당하는 닉네임이 없습니다.");
        }
        return "findPW_form";
    }
}