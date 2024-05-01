package com.example.spring_project1.community.domain.Comment.Dto;

import com.example.spring_project1.community.domain.Comment.Entity.Comment;
import com.example.spring_project1.community.domain.Post.Entity.Post;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class CommentUpdateDto {
    private Long id;
    private String pw;
    private String content;

    public Comment toEntity() {
        return Comment.builder()
            .id(id)
            .pw(pw)
            .content(content)
            .build();
    }
}
