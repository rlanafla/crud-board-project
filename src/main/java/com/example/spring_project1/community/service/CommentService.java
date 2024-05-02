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
    private final PostService postService;

    @Autowired
    public CommentService(CommentRepository commentRepository, PostService postService) {
        this.commentRepository = commentRepository;
        this.postService = postService;
    }

    //전체 댓글 조회
    public List<CommentResponseDto> findComments(long id) {
        PostResponseDto post = postService.findPost(id);
        return commentRepository.findAll().stream().map(Comment::tocommentResponseDto).collect(
        Collectors.toList());}

    //
//    public CommentResponseDto findComment(long id) { return commentRepository.findById(id).map(Comment::tocommentResponseDto).orElseThrow(() -> new EntityNotFoundException()); }

    //댓글 생성
    public CommentResponseDto createComment(Comment comment) { return commentRepository.save(comment).tocommentResponseDto(); }

    //댓글 수정
    public CommentResponseDto updateComment(Comment comment) { return commentRepository.save(comment).tocommentResponseDto(); }

    //댓글 삭제
    public void deleteComment(long id) { commentRepository.deleteById(id); }
}
