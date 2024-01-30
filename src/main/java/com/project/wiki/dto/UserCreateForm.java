package com.project.wiki.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserCreateForm {

    @Size(min = 2, max = 8)
    @NotEmpty(message = "이름은 필수항목입니다.")
    private String username;

    @Size(min = 2, max = 15)
    @NotEmpty(message = "닉네임은 필수항목입니다. (2~15자)")
    private String nickname;

    @NotEmpty(message = "비밀번호는 필수항목입니다.")
    private String password1;

    /**  비밀번호 확인 항목인데, 생각해보면 회원가입시에만 필요한 속성임. 그래서 로그인 할때는 필요 없음.  */
    @NotEmpty(message = "비밀번호 확인은 필수항목입니다.")
    private String password2;

    @NotEmpty(message = "이메일은 필수항목입니다.")
    @Email // 이메일 형식인지 확인해줌
    private String email;
}