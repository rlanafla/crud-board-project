package com.example.spring_project1.community.domain.Board.Entity;

import com.example.spring_project1.community.domain.Board.Dto.BoardResponseDto;
import jakarta.persistence.*;
import java.time.LocalDateTime;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
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

    private LocalDateTime createdAt;

    private LocalDateTime modifiedAt;

    public Board() {}

    public Board(Long id, String pw, String title, String sub_title, LocalDateTime createdAt, LocalDateTime modifiedAt) {
        this.id = id;
        this.pw = pw;
        this.title = title;
        this.sub_title = sub_title;
        this.createdAt = createdAt;
        this.modifiedAt = modifiedAt;
    }
    public Board(String pw, String title, String sub_title, LocalDateTime createdAt, LocalDateTime modifiedAt) {
        this.pw = pw;
        this.title = title;
        this.sub_title = sub_title;
        this.createdAt = createdAt;
        this.modifiedAt = modifiedAt;
    }
    public Long getId() {
        return id;
    }

    public String getPw() {
        return pw;
    }

    public String getTitle() {
        return title;
    }

    public String getSub_title() {
        return sub_title;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public LocalDateTime getModifiedAt() {
        return modifiedAt;
    }

    public void setId(Long id) {
        this.id = id;
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

    public BoardResponseDto toBoardResponseDto() {
        return new BoardResponseDto(id, pw, title, sub_title, createdAt, modifiedAt);
    }
}