package com.example.spring_project1.community.domain.Comment.Dto;

import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class CommentResponseDto {
    private Long id;

    private String pw;

    private String content;

    private LocalDateTime createdAt;

    private LocalDateTime modifiedAt;
}
