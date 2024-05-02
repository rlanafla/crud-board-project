package com.example.spring_project1.community.controller;
import com.example.spring_project1.community.domain.Board.Dto.BoardResponseDto;
import com.example.spring_project1.community.domain.Board.Entity.Board;
import com.example.spring_project1.community.domain.Post.Dto.PostRequestDto;
import com.example.spring_project1.community.domain.Post.Dto.PostResponseDto;
import com.example.spring_project1.community.domain.Post.Dto.PostUpdateDto;
import com.example.spring_project1.community.domain.Post.Entity.Post;
import java.util.List;
import java.util.Optional;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import com.example.spring_project1.community.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("boards/{boardid}/posts")
public class PostViewController {
    private final PostService postService;

    @Autowired
    public PostViewController(PostService postService) {
        this.postService = postService;
    }

    //게시글 목록
    @GetMapping
    public String getAllPostView(@PathVariable("boardid") long boardid, Model model) {
        List<PostResponseDto> posts = postService.findPosts();
        model.addAttribute("postlist", posts);
        model.addAttribute("boardid", boardid);
        return "board2"; }

    //게시글 생성 페이지
    @GetMapping("/createpost")
    public String getCreatePostView(@PathVariable("boardid") long boardid, Model model) {
        model.addAttribute("boardid", boardid);

        return "boardcreate"; }

    //특정 게시글 조회
    @GetMapping("/{postid}")
    public String getPostView(@PathVariable("postid") long postid, @PathVariable("boardid") long boardid, Model model) {
        model.addAttribute("boardid", boardid);
        PostResponseDto post = postService.findPost(postid);
        model.addAttribute("post2", post);
        return "post"; }

    //특정 게시글 id 조회
    @GetMapping("/createpost/{postid}")
    public ResponseEntity<?> getPostById(@PathVariable("boardid") long boardid, @PathVariable("postid") long postid, Model model) {
        Optional<PostResponseDto> post = Optional.ofNullable(postService.findPost(postid));
        if (!post.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Invalid post ID: " + postid);
        }
        model.addAttribute("post", post.get());
        model.addAttribute("board", boardid);
        return ResponseEntity.ok(post);
    }

    //특정 게시판 수정 페이지
    @GetMapping("/edit/{postid}")
    public String editPostView(@PathVariable("boardid") long boardid, @PathVariable("postid") long postid, Model model)
        throws IllegalAccessException {
            Optional<PostResponseDto> post = Optional.ofNullable(postService.findPost(postid));
            //.해당 id를 가진 board가 존재하지 않으면
            if (!post.isPresent()) {
                throw new IllegalAccessException("invalid board" + postid);
            }
            model.addAttribute("post", post.get());
            model.addAttribute("boardid", boardid);
            return "boardedit";}

    //게시판 생성(제출 시)
    @PostMapping("/createpost")
    public String createPost(@PathVariable("boardid") long boardid, @RequestParam String title,
        @RequestParam String pw, @RequestParam String content, @RequestParam String location, Model model){
        PostRequestDto postRequestDto = new PostRequestDto(pw, title, content, location);
        postService.createPost(boardid, postRequestDto);
        model.addAttribute("boardid", boardid);
        return "redirect:/boards/{boardid}/posts";
    }

    //게시판 수정하기(제출 시)
    @PostMapping("/edit/{postid}")
    public String editPost(@PathVariable("boardid") long boardid, @PathVariable("postid") long postid, @RequestParam String title,
        @RequestParam String pw, @RequestParam String content, @RequestParam String location, Model model){
        PostUpdateDto postUpdateDto = new PostUpdateDto(postid, pw, title, content, location);
        postService.updatePost(postUpdateDto);
        model.addAttribute("boardid", boardid);
        return "redirect:/boards/{boardid}/posts";
    }

    //게시판 삭제
    @GetMapping("/delete/{postid}")
    public String deletePost(@PathVariable("boardid") long boardid, @PathVariable("postid") long postid, Model model){
        postService.deletePost(postid);
        model.addAttribute("boardid", boardid);
        return "redirect:/boards/{boardid}/posts";
    }
}
