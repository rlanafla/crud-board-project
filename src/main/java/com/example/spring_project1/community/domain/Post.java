package com.example.spring_project1.community.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Post {
    @Id
    //게시글 생성 시 ID 생성을
    @GeneratedValue(strategy = GenerationType.IDENTITY)
}
