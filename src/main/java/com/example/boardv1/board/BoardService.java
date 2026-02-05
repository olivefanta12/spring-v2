package com.example.boardv1.board;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.boardv1.user.User;

import lombok.RequiredArgsConstructor;

//책임 : 트랜잭션관리, DTO만들기, 권한체크(DB정보가 필요하니까)
@RequiredArgsConstructor
@Service // 트랜잭션 컴퍼넌트
public class BoardService {

    private final BoardRepository boardRepository;

    public List<Board> 게시글목록() {
        return boardRepository.findAll();
    }

    public BoardResponse.DetailDTO 상세보기(int id, Integer sessionUserid) {
        Board board = boardRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("아이디를 찾을수가 없어요"));

        return new BoardResponse.DetailDTO(board, sessionUserid);

    }

    public Board 수정폼게시글정보(int id, int sessionUserid) {
        Board board = boardRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("아이디를 찾을수가 없어요"));

        if (sessionUserid != board.getUser().getId()) {
            throw new RuntimeException("권한이 없어요");
        }

        return board;

    }

    @Transactional // update, delete, insert 할때 붙혀 auto flush 해줌
    public void 게시글수정(int id, String title, String content, int sessionUserid) {
        Board board = boardRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("아이디를 찾을수가 없어요"));

        board.setTitle(title);
        board.setContent(content);

        if (sessionUserid != board.getUser().getId()) {
            throw new RuntimeException("권한이 없어요");
        }
    }

    // ACID A : 원자성 (모든게 다 되면 commit, 하나라도 실패하면 rollback)
    // 트랜잭션 종료시 flush 됨
    @Transactional
    public void 게시글쓰기(String title, String content, User user) {
        // 1. 비영속 개체
        Board board = new Board();
        board.setTitle(title);
        board.setContent(content);
        board.setUser(user);

        System.out.println("영속화 전" + board.getId());

        // 2. persist 영속화
        boardRepository.save(board);

        System.out.println("영속화" + board.getId());
    }

    @Transactional
    public void 게시글삭제(int id, int sessionUserid) {
        Board board = boardRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("아이디를 찾을수가 없어요"));

        if (sessionUserid != board.getUser().getId()) {
            throw new RuntimeException("삭제할 권한이 없어요");
        }
        boardRepository.delete(board);
    }

}
