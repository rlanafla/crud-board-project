package com.example.spring_project1.community.service;

import com.example.spring_project1.community.domain.Comment.Dto.CommentRequestDto;
import com.example.spring_project1.community.domain.Comment.Dto.CommentResponseDto;
import com.example.spring_project1.community.domain.Comment.Dto.CommentUpdateDto;
import com.example.spring_project1.community.domain.Comment.Entity.Comment;
import com.example.spring_project1.community.domain.Post.Dto.PostResponseDto;
import com.example.spring_project1.community.domain.Post.Dto.PostUpdateDto;
import com.example.spring_project1.community.domain.Post.Entity.Post;
import com.example.spring_project1.community.repository.CommentRepository;
import com.example.spring_project1.community.repository.PostRepository;
import jakarta.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CommentService {
    private final CommentRepository commentRepository;
    private final PostRepository postRepository;

    @Autowired
    public CommentService(CommentRepository commentRepository, PostRepository postRepository) {
        this.commentRepository = commentRepository;
        this.postRepository = postRepository;
    }

    //해당 post의 전체 댓글 조회
    public List<CommentResponseDto> findComments(long id) {
        //post repository에서 해당 id로 된 Post 찾기
        Post post = postRepository.findById(id).get();
        List<Comment> commentList = commentRepository.findAllByPostId(id);
        return commentList.stream().map(Comment::tocommentResponseDto).collect(Collectors.toList());
    }


    //특정 댓글 조회
    public CommentResponseDto findComment(long id) {
        Comment comment = commentRepository.findById(id).get();
        return comment.tocommentResponseDto();
    }

    //댓글 생성
    public CommentResponseDto createComment(long postid, CommentRequestDto commentRequestDto) {
        Post post = postRepository.findById(postid).get();
        Comment comment = new Comment().builder()
            .pw(commentRequestDto.getPw())
            .content(commentRequestDto.getContent())
            .post_id(post)
            .build();

        commentRepository.save(comment);
        return comment.tocommentResponseDto();
    }

    //댓글 수정
    public CommentResponseDto updateComment(CommentUpdateDto commentUpdateDto) {
        Comment comment = commentRepository.findById(commentUpdateDto.getId()).get();
        comment.setPw(commentUpdateDto.getPw());
        comment.setContent(commentUpdateDto.getContent());
            //post 수정사항 저장
        commentRepository.save(comment);
        return comment.tocommentResponseDto();
    }
    //댓글 삭제
    public void deleteComment(long id) { commentRepository.deleteById(id); }
}
