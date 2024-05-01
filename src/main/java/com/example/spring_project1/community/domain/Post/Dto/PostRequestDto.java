package com.example.spring_project1.community.domain.Post.Dto;

import com.example.spring_project1.community.domain.Board.Entity.Board;
import com.example.spring_project1.community.domain.Post.Entity.Post;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Setter
@Getter
public class PostRequestDto {
    private String pw;
    private String title;
    private String content;
    private String location;
}
