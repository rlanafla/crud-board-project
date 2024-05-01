package com.example.spring_project1.community.domain.Comment.Dto;

import com.example.spring_project1.community.domain.Comment.Entity.Comment;
import lombok.AllArgsConstructor;
import lombok.Setter;


@AllArgsConstructor
@Setter
public class CommentRequestDto {
    private String pw;
    private String content;

    public Comment toEntity() {
        return Comment.builder()
            .pw(pw)
            .content(content)
            .build();
    }
}
