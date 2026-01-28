package com.example.boardv1.board;

import lombok.Data;

@Data
public class BoardSaveDTO { // Data Transfer Object
    private String title;
    private String content;
}
