package com.example.boardv1.user;

import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class UserService {
    private final UserRepository userRepository;

    @Transactional
    public void 회원가입(String username, String password, String email) {
        // 1. 유저네임 중복체크
        User findUser = userRepository.findByUsername(username);
        if (findUser != null) {
            throw new RuntimeException("유저네임이 중복되었습니다");
        }
        // 2. 비영속개체
        User user = new User();
        user.setUsername(username);
        user.setPassword(password);
        user.setEmail(email);

        // 3. persist
        userRepository.save(user);
    }
}
