package com.example.spring_project1.community.controller;

import com.example.spring_project1.community.domain.Comment.Dto.CommentRequestDto;
import com.example.spring_project1.community.domain.Comment.Dto.CommentResponseDto;
import com.example.spring_project1.community.domain.Post.Dto.PostRequestDto;
import com.example.spring_project1.community.domain.Post.Dto.PostResponseDto;
import com.example.spring_project1.community.service.CommentService;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/boards/{boardid}/{postid}")
public class CommentController {
    private final CommentService commentService;

    @Autowired
    public CommentController(CommentService commentService) { this.commentService = commentService; }

    //특정 id 댓글 조회
    @GetMapping("/{commentid}")
    public ResponseEntity<?> getCommentById(@PathVariable long commentid, Model model) {
        Optional<CommentResponseDto> comment = Optional.ofNullable(commentService.findComment(commentid));
        if (!comment.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Invalid comment ID: " + commentid);
        }
        model.addAttribute("post", comment.get());
        return ResponseEntity.ok(comment);
    }

    //댓글 생성
    @PostMapping("/createcomment")
    public String createComment(@RequestParam String pw, @RequestParam String content) {
        CommentRequestDto commentRequestDto = new CommentRequestDto(pw, content);
        CommentResponseDto commentResponseDto = commentService.createComment(commentRequestDto.toEntity());
        return "redirect:/boards/{boardid}/{postid}";
    }

    //댓글 수정
    @GetMapping("/edit/{commentid}")
    public String editCommentView(@PathVariable long commentid, Model model)
        throws IllegalAccessException {
        Optional<CommentResponseDto> comment = Optional.ofNullable(commentService.findComment(commentid));
        //.해당 id를 가진 board가 존재하지 않으면
        if (!comment.isPresent()) {
            throw new IllegalAccessException("invalid comment" + commentid);
        }
        model.addAttribute("comment", comment.get());
        return "boardedit";
    }
}
