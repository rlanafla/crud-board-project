package com.example.spring_project1.community.domain.Post.Dto;

import com.example.spring_project1.community.domain.Post.Entity.Post;
import lombok.AllArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@Setter
public class PostRequestDto {
    private String pw;
    private String title;
    private String content;
    private String location;


    public Post toEntity() {
        return Post.builder()
            .pw(pw)
            .title(title)
            .content(content)
            .location(location)
            .build();
    }
}
