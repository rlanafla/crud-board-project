package com.example.spring_project1.community.domain.Board.Dto;

import com.example.spring_project1.community.domain.Board.Entity.Board;
import jakarta.persistence.Column;
import java.time.LocalDateTime;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BoardPostDto {
    private String pw;

    private String title;

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


    public Board toEntity() {
        Board board = new Board(pw, title, sub_title, createdAt, modifiedAt);
        return board;
    }
}