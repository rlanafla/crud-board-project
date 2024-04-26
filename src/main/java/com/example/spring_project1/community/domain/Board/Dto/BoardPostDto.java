package com.example.spring_project1.community.domain.Board.Dto;

import com.example.spring_project1.community.domain.Board.Entity.Board;
import jakarta.persistence.Column;
import java.time.LocalDateTime;

public class BoardPostDto {
    @Column(length = 10, nullable = false)
    private String pw;

    @Column(length = 20, nullable = false)
    private String title;

    @Column(length = 17, nullable = false)
    private String sub_title;

    private LocalDateTime createdAt;

    private LocalDateTime modifiedAt;

    public BoardPostDto() {}

    public BoardPostDto(String pw, String title, String sub_title, LocalDateTime createdAt, LocalDateTime modifiedAt) {
        this.pw = pw;
        this.title = title;
        this.sub_title = sub_title;
        this.createdAt = createdAt;
        this.modifiedAt = modifiedAt;
    }
    public void setPw(String pw) {
        this.pw = pw;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setSub_title(String sub_title) {
        this.sub_title = sub_title;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public void setModifiedAt(LocalDateTime modifiedAt) {
        this.modifiedAt = modifiedAt;
    }

    //받은 값을 entity로 생성

    public Board toEntity() {
        Board board = new Board(pw, title, sub_title, createdAt, modifiedAt);
        return board;
    }
}