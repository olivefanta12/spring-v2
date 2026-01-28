package com.example.boardv1.board;

import java.util.List;

import org.springframework.stereotype.Repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import lombok.RequiredArgsConstructor;

/*
 하이버네이트 기술
*/

//BoardRepository는 EntityManger에 의존함 의존성파일
@RequiredArgsConstructor // final이 붙어있는 모든 필드를 초기화하는 생성자를 만듬
@Repository // DB에 접근하는 진입점
public class BoardRepository {

    private final EntityManager em; // null

    // DI 의존성 주입(의존하는게 IOC에 띄워줘야함)
    // public BoardRepository(EntityManager em) {
    // this.em = em;
    // }

    public Board findById(int id) {
        Board board = em.find(Board.class, id);
        return board;
    }

    public List<Board> findAll() {
        Query query = em.createQuery("select b from Board b order by b.id desc", Board.class); // 객체 지향 쿼리
        List<Board> list = query.getResultList();
        return list;
    }

    public void findAllV2() {
        em.createQuery("select b.id, b.title from Board b").getResultList();
    }

    public Board save(Board board) {
        em.persist(board); // 영속화(영구히 저장하다) insert code
        return board;
    }

    public void delete(Board board) {
        em.remove(board);
    }

}
