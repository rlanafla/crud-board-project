package com.example.spring_project1.community.repository;

import com.example.spring_project1.community.domain.Comment.Entity.Comment;
import com.example.spring_project1.community.domain.Post.Entity.Post;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface CommentRepository extends JpaRepository<Comment, Long> {
    //post의 모든 comment 찾기
    @Query("select c from Comment c where c.post_id.id = :postId")
    List<Comment> findAllByPostId(@Param("postId") long postId);
}
