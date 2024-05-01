package com.example.spring_project1.community.repository;

import com.example.spring_project1.community.domain.Comment.Entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment, Long> {

}
