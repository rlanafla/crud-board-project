package com.example.spring_project1.community.controller;
import com.example.spring_project1.community.domain.Board.Dto.BoardResponseDto;
import com.example.spring_project1.community.domain.Post.Dto.PostRequestDto;
import com.example.spring_project1.community.domain.Post.Dto.PostResponseDto;
import com.example.spring_project1.community.domain.Post.Dto.PostUpdateDto;
import java.util.Optional;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import com.example.spring_project1.community.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("boards/{boardid}")
public class PostViewController {
    private final PostService postService;

    @Autowired
    public PostViewController(PostService postService) {
        this.postService = postService;
    }

    //게시글 목록
    @GetMapping
    public String getAllPostView(Model model) {return "board2";}

    //게시글 생성 페이지
    @GetMapping("/createpost")
    public String getCreatePostView(Model model) {return "boardcreate";}

    //특정 게시글 id 조회
    @GetMapping("/createpost/{postid}")
    public ResponseEntity<?> getPostById(@PathVariable long postid, Model model) {
        Optional<PostResponseDto> post = Optional.ofNullable(postService.findPost(postid));
        if (!post.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Invalid board ID: " + postid);
        }
        model.addAttribute("post", post.get());
        return ResponseEntity.ok(post);
    }

    //특정 게시판 수정 페이지
    @GetMapping("/edit/{postid}")
    public String editPostView(@PathVariable long postid, Model model)
        throws IllegalAccessException {
            Optional<PostResponseDto> post = Optional.ofNullable(postService.findPost(postid));
            //.해당 id를 가진 board가 존재하지 않으면
            if (!post.isPresent()) {
                throw new IllegalAccessException("invalid board" + postid);
            }
            model.addAttribute("board", post.get());
            return "boardedit";}

    //게시판 생성(제출 시)
    @PostMapping("/createpost")
    public String createPost(@RequestParam String title, @RequestParam String location, @RequestParam String pw, @RequestParam String content){
        PostRequestDto postRequestDto = new PostRequestDto(pw, title, content, location);
        PostResponseDto postResponseDto = postService.createPost(postRequestDto.toEntity());
        return "redirect:/boards/{boardid}";
    }

    //게시판 수정하기(제출 시)
    @PostMapping("/edit/{postid}")
    public String editPost(@PathVariable long postid, @RequestParam String title, @RequestParam String location, @RequestParam String pw, @RequestParam String content){
        PostUpdateDto postUpdateDto = new PostUpdateDto(postid, title, location, pw, content);
        postService.updatePost(postUpdateDto.toEntity());

        return "redirect:/boards/{boardid}";
    }

    //게시판 삭제
    @GetMapping("/delete/{postid}")
    public String deletePost(@PathVariable long postid){
        postService.deletePost(postid);
        return "redirect:/boards/{boardid}";
    }
}
