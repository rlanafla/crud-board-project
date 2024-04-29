package com.example.spring_project1.community.repository;

import com.example.spring_project1.community.domain.Post.Entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post, Long> {

}
