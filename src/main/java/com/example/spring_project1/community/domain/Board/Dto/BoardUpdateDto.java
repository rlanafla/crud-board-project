package com.example.spring_project1.community.domain.Board.Dto;
import com.example.spring_project1.community.domain.Board.Entity.Board;
import jakarta.persistence.Column;
import java.time.LocalDateTime;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BoardUpdateDto {
    private Long id;
    private String pw;

    private String title;

    private String sub_title;

    private LocalDateTime created_at;

    private LocalDateTime modified_at;

    public BoardUpdateDto() {}

    public BoardUpdateDto(Long id, String pw, String title, String sub_title, LocalDateTime created_at, LocalDateTime modified_at) {
        this.id = id;
        this.pw = pw;
        this.title = title;
        this.sub_title = sub_title;
        this.created_at = created_at;
        this.modified_at = modified_at;
    }

    //받은 값을 entity로 생성

    public Board toEntity() {
        Board board = new Board(id, pw, title, sub_title, created_at, modified_at);
        return board;
    }
}