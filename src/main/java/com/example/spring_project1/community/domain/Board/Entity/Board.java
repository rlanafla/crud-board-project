package com.example.spring_project1.community.domain.Board.Entity;

import com.example.spring_project1.community.domain.Board.Dto.BoardResponseDto;
import jakarta.persistence.*;
import java.time.LocalDateTime;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "board")
public class Board {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 10, nullable = false)
    private String pw;

    @Column(length = 20, nullable = false)
    private String title;

    @Column(length = 17, nullable = false)
    private String sub_title;

    @Column(name = "created_at")
    private LocalDateTime created_at;

    @Column(name = "modified_at")
    private LocalDateTime modified_at;

    public Board() {}

    public Board(Long id, String pw, String title, String sub_title, LocalDateTime createdAt, LocalDateTime modifiedAt) {
        this.id = id;
        this.pw = pw;
        this.title = title;
        this.sub_title = sub_title;
        this.created_at = createdAt;
        this.modified_at = modifiedAt;
    }
    public Board(String pw, String title, String sub_title, LocalDateTime createdAt, LocalDateTime modifiedAt) {
        this.pw = pw;
        this.title = title;
        this.sub_title = sub_title;
        this.created_at = createdAt;
        this.modified_at = modifiedAt;
    }

    public BoardResponseDto toBoardResponseDto() {
        return new BoardResponseDto(id, pw, title, sub_title, created_at, modified_at);
    }
}