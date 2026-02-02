package com.example.boardv1.user;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.data.jpa.test.autoconfigure.DataJpaTest;
import org.springframework.context.annotation.Import;

import com.example.boardv1.board.BoardRepository;

import jakarta.persistence.EntityManager;

@Import(UserRepository.class)
@DataJpaTest
public class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    @Test
    public void save_test() {
        // given
        User user = new User(); // 비영속개체
        user.setUsername("love");
        user.setPassword("1234");
        user.setEmail("love@nate.com");

        // when
        User findUser = userRepository.save(user); // 영속화된 개체

        // eye
        System.out.println(findUser);

    }

    @Test
    public void save_fail_test() {
        // given
        User user = new User(); // 비영속개체
        user.setUsername("cos");
        user.setPassword("1234");
        user.setEmail("love@nate.com");

        // when
        User findUser = userRepository.save(user); // 영속화된 개체

        // eye
        System.out.println(findUser);

    }

    @Test
    public void findByUsername_test() {

        // given
        String username = "ssar";
        // when
        User findUser = userRepository.findByUsername(username);

        // eye
        System.out.println(findUser);
    }

}
