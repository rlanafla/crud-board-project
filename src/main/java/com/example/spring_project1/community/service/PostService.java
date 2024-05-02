package com.example.spring_project1.community.service;

import com.example.spring_project1.community.domain.Board.Dto.BoardResponseDto;
import com.example.spring_project1.community.domain.Board.Entity.Board;
import com.example.spring_project1.community.domain.Post.Dto.PostRequestDto;
import com.example.spring_project1.community.domain.Post.Dto.PostResponseDto;
import com.example.spring_project1.community.domain.Post.Dto.PostUpdateDto;
import com.example.spring_project1.community.domain.Post.Entity.Post;
import com.example.spring_project1.community.repository.JdbcTemplateBoardRepository;
import com.example.spring_project1.community.repository.PostRepository;
import jakarta.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

@Service
public class PostService {

    private final PostRepository postRepository;
    private final BoardService boardService;

    @Autowired
    public PostService(PostRepository postRepository,
        BoardService boardService) {
        this.postRepository = postRepository;
        this.boardService = boardService;
    }

    //게시글 조회
    public List<PostResponseDto> findPosts(long id) {
        Board board = boardService.findBoard(id).toEntity();
        List<Post> post2 = postRepository.findAllByBoardId(id);
        return post2.stream().map(Post::toPostResponseDto).collect(Collectors.toList());
    }

    //id로 게시글 조회
    public PostResponseDto findPost(long id) {
        //board에서 board를 찾는다(response -> entity) Board 타입으로 가져오기 위함
        //post id로 된 post를 찾는다. (Optional 반환)
        Post post = postRepository.findById(id).get();
        //post의 boardid를 board 로 set해준다.
        return post.toPostResponseDto();

    }
    //게시글 생성
    public PostResponseDto createPost(long boardid, PostRequestDto postRequestDto) {
        //해당 id로 된 board 찾기
        Board board = boardService.findBoard(boardid).toEntity();
        //새 post를 만들고 post 의 board_id에 set하기
        Post post = new Post().builder()
            .pw(postRequestDto.getPw())
            .board_id(board)
            .title(postRequestDto.getTitle())
            .content(postRequestDto.getContent())
            .location(postRequestDto.getLocation())
            .build();
        //post 저장
        postRepository.save(post);
        //responsedto로 반환
        return post.toPostResponseDto();
    }

    //게시글 수정
    public PostResponseDto updatePost(PostUpdateDto postUpdateDto) {
        //Board board = boardService.findBoard(postUpdateDto.getBoard_id()).toEntity();
        //post id로 된 board를 찾고 내용 변경
        Post post = postRepository.findById(postUpdateDto.getId()).get();
        post.setPw(postUpdateDto.getPw());
        post.setTitle(postUpdateDto.getTitle());
        post.setContent(postUpdateDto.getContent());
        post.setLocation(postUpdateDto.getLocation());
        //post 수정사항 저장
        postRepository.save(post);
        return post.toPostResponseDto();
    }

    //게시글 삭제
    public void deletePost(long id) {
        postRepository.deleteById(id);
    }
}
