package com.example.boardv1.reply;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.boardv1.board.Board;
import com.example.boardv1.user.User;

import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class ReplyService {

    private final ReplyRepository replyRepository;
    private final EntityManager em;

    @Transactional
    public void 댓글쓰기(String comment, Integer boardId, User sessionUserId) {
        Reply reply = new Reply();

        Board board = em.getReference(Board.class, boardId);
        User user = em.getReference(User.class, sessionUserId.getId());
        reply.setComment(comment);
        reply.setBoard(board);
        reply.setUser(user);

        replyRepository.save(reply);

    }

    @Transactional
    public void 댓글삭제(int id, Integer sessionUserid) {
        // 1. 댓글찾기
        Reply reply = replyRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("에에에ㅔㅔㅔㅔㅔㅔㅔㅔㅔㅔㅔㅔㅔㅔㅔ러"));

        // 2. 권한체크
        if (reply.getUser().getId() != sessionUserid)
            throw new RuntimeException("에ㅔㅔㅔㅔㅔㅔㅔㅔㅔㅔㅔㅔㅔㅔㅔㅔㅔㅔ러");

        // 3. 댓글 삭제
        replyRepository.delete(reply);
    }

}
