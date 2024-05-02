package com.example.spring_project1.community.domain.Post.Dto;

import com.example.spring_project1.community.domain.Board.Entity.Board;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;

@Getter
@AllArgsConstructor

public class PostResponseDto {
    private Long id;

    private String pw;

    private String title;

    private String content;

    private String location;

    private int like_count;

    private LocalDateTime created_at;

    private LocalDateTime modified_at;
}
