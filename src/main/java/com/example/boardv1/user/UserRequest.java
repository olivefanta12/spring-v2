package com.example.boardv1.user;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

public class UserRequest {
    @Data
    public static class JoinDTO {
        @NotBlank(message = "유저이름을 입력해주세요")
        @Size(min = 3, max = 20, message = "유저이름을 3~20 자리로 입력해주세요")
        private String username;

        @NotBlank(message = "비밀번호를 입력해주세요")
        @Size(min = 4, max = 20, message = "비밀번호를 4~20 자리로 입력해주세요")
        private String password;

        @Email(message = "이메일 형식이 올바르지 않습니다")
        @NotBlank(message = "이메일을 입력해주세요")
        private String email;
    }

    @Data
    public static class LoginDTO {
        @NotBlank(message = "유저이름을 입력해주세요")
        private String username;

        @NotBlank(message = "비밀번호를 입력해주세요")
        private String password;
    }
}
