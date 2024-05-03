package com.example.spring_project1.community.controller;

import com.example.spring_project1.community.domain.Comment.Dto.CommentRequestDto;
import com.example.spring_project1.community.domain.Comment.Dto.CommentResponseDto;
import com.example.spring_project1.community.domain.Comment.Dto.CommentUpdateDto;
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
//댓글 수정 post
//pw 기능은 추가하지 않아서 default value로 세팅
@Controller
@RequestMapping("/boards/{boardid}/posts/{postid}")
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
        model.addAttribute("comment2", comment.get());
        //model.addAttribute("postid", postid);
        return ResponseEntity.ok(comment);
    }


    //댓글 생성
    @PostMapping("/createcomment")
    public String createComment(@PathVariable("boardid") long boardid, @PathVariable("postid") long postid, @RequestParam("pw") String pw, @RequestParam("content") String content, Model model) {
        CommentRequestDto commentRequestDto = new CommentRequestDto(pw, content);
        commentService.createComment(postid, commentRequestDto);
        return "redirect:/boards/{boardid}/posts/{postid}";
    }

    //댓글 수정 버튼 누를 시 수정 화면
    @GetMapping("/edit/{commentid}")
    public String editCommentView(@PathVariable("commentid") long commentid, Model model)
        throws IllegalAccessException {
        Optional<CommentResponseDto> comment = Optional.ofNullable(commentService.findComment(commentid));
        //.해당 id를 가진 board가 존재하지 않으면
        if (!comment.isPresent()) {
            throw new IllegalAccessException("invalid comment" + commentid);
        }
        model.addAttribute("comment", comment.get());
        return "post2";
    }

    //댓글 수정 post
    @PostMapping("/edit/{commentid}")
    public String editComment(@PathVariable("commentid") long commentid,
        @RequestParam("pw") String pw, @RequestParam String content, Model model){
        CommentUpdateDto commentUpdateDto = new CommentUpdateDto(commentid, pw, content);
        commentService.updateComment(commentUpdateDto);
        return "redirect:/boards/{boardid}/posts/{postid}";
    }

    @GetMapping("/delete/{commentid}")
    public String deleteComment(@PathVariable("commentid") long commentid){
        commentService.deleteComment(commentid);
        return "redirect:/boards/{boardid}/posts/{postid}";
    }
}
