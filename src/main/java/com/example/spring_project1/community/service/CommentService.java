package com.example.spring_project1.community.service;

import com.example.spring_project1.community.domain.Comment.Dto.CommentResponseDto;
import com.example.spring_project1.community.domain.Comment.Entity.Comment;
import com.example.spring_project1.community.domain.Post.Dto.PostResponseDto;
import com.example.spring_project1.community.domain.Post.Entity.Post;
import com.example.spring_project1.community.repository.CommentRepository;
import jakarta.persistence.EntityNotFoundException;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CommentService {
    private final CommentRepository commentRepository;

    @Autowired
    public CommentService(CommentRepository commentRepository) { this.commentRepository = commentRepository; }

    //게시글 조회
    public List<CommentResponseDto> findComments() { return commentRepository.findAll().stream().map(Comment::tocommentResponseDto).collect(
        Collectors.toList());}

    //id로 게시글 조회
    public CommentResponseDto findComment(long id) { return commentRepository.findById(id).map(Comment::tocommentResponseDto).orElseThrow(() -> new EntityNotFoundException()); }

    //게시글 생성
    public CommentResponseDto createComment(Comment comment) { return commentRepository.save(comment).tocommentResponseDto(); }

    //게시글 수정
    public CommentResponseDto updateComment(Comment comment) { return commentRepository.save(comment).tocommentResponseDto(); }

    //게시글 삭제
    public void deleteComment(long id) { commentRepository.deleteById(id); }
}
