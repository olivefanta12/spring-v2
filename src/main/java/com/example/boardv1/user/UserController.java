package com.example.boardv1.user;

import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.boardv1._core.errors.ex.Exception401;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    private final HttpSession session;

    // 브라우저의 쿠키에 sessionkey값 삭제
    // 30분동안 요청하지않기 (request)
    // 모든 브라우저 종료
    // 서버 로그아웃
    @GetMapping("/logout")
    public String logOut() {
        session.invalidate();
        return "redirect:/";
    }

    @GetMapping("/login-form")
    public String loginForm() {
        return "user/login-form";
    }

    @GetMapping("/join-form")
    public String joinForm() {
        return "user/join-form";
    }

    @PostMapping("/join")
    public String join(@Valid UserRequest.JoinDTO reqDTO, Errors errors) {
        userService.회원가입(reqDTO.getUsername(), reqDTO.getPassword(), reqDTO.getEmail());
        return "redirect:/login-form";
    }

    // 조회인데, 예외로 post요청
    @PostMapping("/login")
    public String login(@Valid UserRequest.LoginDTO reqDTO, Errors errors, HttpServletResponse resp) {
        // HttpSession session = req.getSession(); // 유일하니까(singleton) ioc에 띄움

        User sessionUser = userService.로그인(reqDTO.getUsername(), reqDTO.getPassword());
        if (sessionUser == null) {
            throw new Exception401("인증되지 않았습니다");
        }
        session.setAttribute("sessionUser", sessionUser);
        System.out.println(sessionUser);
        // http Response header에 Set-cookie : sessionkey값에 자동저장되서 응답됨.

        Cookie cookie = new Cookie("username", sessionUser.getUsername());
        cookie.setHttpOnly(false);
        resp.addCookie(cookie);
        return "redirect:/";
    }
}
