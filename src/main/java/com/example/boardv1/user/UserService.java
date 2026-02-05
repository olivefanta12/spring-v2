package com.example.boardv1.user;

import java.util.Optional;

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
        Optional<User> optUser = userRepository.findByUsername(username);

        if (optUser.isPresent()) {
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

    public User 로그인(String username, String password) {
        User findUser = userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("username을 찾을수 없어요"));

        if (!findUser.getPassword().endsWith(password)) {
            throw new RuntimeException("패스워드가 일치하지 않아요");
        }
        return findUser;
    }
}
