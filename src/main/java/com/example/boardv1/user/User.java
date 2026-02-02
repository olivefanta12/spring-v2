package com.example.boardv1.user;

import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;
import org.springframework.context.annotation.EnableLoadTimeWeaving;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor // object mapping을 hibernate가 할때 디폴트 생성자를 new한다.
@Data
@Entity
@Table(name = "user_tb")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(unique = true) // pk, uk 일떄 index를 만들어준다. column = 제약조건
    private String username;

    @Column(nullable = false, length = 100)
    private String password;
    private String email;

    @CreationTimestamp
    private LocalDateTime createdAt;
}
