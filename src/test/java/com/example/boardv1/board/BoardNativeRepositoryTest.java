package com.example.boardv1.board;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.data.jpa.test.autoconfigure.DataJpaTest;
import org.springframework.context.annotation.Import;

@Import(BoardNativeRepository.class)
@DataJpaTest
public class BoardNativeRepositoryTest {
    @Autowired
    private BoardNativeRepository boardNativeRepository;

    @Test
    public void findById_test() {
        // given
        int id = 1;

        // when
        Board board = boardNativeRepository.findById(id);
        // eye
        System.out.println(board);
    }

    @Test
    public void findAll_test() {
        List<Board> list = boardNativeRepository.findAll();
        for (Board board : list) {
            System.out.println(board);
        }
    }

    @Test
    public void save_test() {

        String title = "title";
        String content = "content";
        boardNativeRepository.save(title, content);

        findAll_test();

    }

    @Test
    public void deleteById_test() {
        int id = 1;

        boardNativeRepository.deleteById(id);

        findAll_test();

    }

    @Test
    public void updateById_test() {

        int id = 1;
        String title = "title10";
        String content = "content10";

        boardNativeRepository.updateById(id, title, content);

        findAll_test();

    }
}
