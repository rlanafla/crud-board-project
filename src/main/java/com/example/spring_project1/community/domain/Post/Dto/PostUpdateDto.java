package com.example.spring_project1.community.domain.Post.Dto;

import com.example.spring_project1.community.domain.Post.Entity.Post;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class PostUpdateDto {
    private Long id;
    private String pw;
    private String title;
    private String content;
    private String location;

    public Post toEntity() {
        return Post.builder()
            .id(id)
            .pw(pw)
            .title(title)
            .content(content)
            .location(location)
            .build();
    }
}
