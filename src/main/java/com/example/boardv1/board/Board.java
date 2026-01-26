package com.example.boardv1.board;

import java.sql.Timestamp;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

/*
 데이터 베이스 세상의 테이블을 자바 세상에 모델링한 결과 = 엔티티
*/

@NoArgsConstructor // default 생성자
@Data // Getter Setter toString
@Entity // 해당 어노테이션을 보고, 컴퍼넌트 스캔 후, 데이터베이스에 테이블을 생성
@Table(name = "board_tb")
public class Board {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String title;
    private String content;
    private Timestamp createdAt;
}
