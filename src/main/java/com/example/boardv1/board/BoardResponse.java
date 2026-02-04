package com.example.boardv1.board;

import com.example.boardv1.user.User;

import lombok.Data;

@Data
public class BoardResponse {
    public static class DetailDTO {

        // 화면에 안보이는거
        private int id;
        private int userid;

        // 회면에 보이는거
        private String title;
        private String content;
        private String username;

        // 연산해서 만들어야 하는거
        private boolean isOwner;

        public DetailDTO(Board board, Integer sessionUserid) {
            this.id = board.getId();
            this.userid = board.getUser().getId();
            this.title = board.getTitle();
            this.content = board.getContent();
            this.username = board.getUser().getUsername();
            this.isOwner = board.getUser().getId() == sessionUserid;
        }

    }
}
