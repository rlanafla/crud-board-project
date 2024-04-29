package com.example.spring_project1.community.service;

import com.example.spring_project1.community.domain.Post.Dto.PostResponseDto;
import com.example.spring_project1.community.domain.Post.Entity.Post;
import com.example.spring_project1.community.repository.PostRepository;
import jakarta.persistence.EntityNotFoundException;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PostService {

    private final PostRepository postRepository;

    @Autowired
    public PostService(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    //게시글 조회
    public List<PostResponseDto> findPosts() { return postRepository.findAll().stream().map(Post::toPostResponseDto).collect(
        Collectors.toList());}

    //id로 게시글 조회
    public PostResponseDto findPost(long id) { return postRepository.findById(id).map(Post::toPostResponseDto).orElseThrow(() -> new EntityNotFoundException()); }

    //게시글 생성
    public PostResponseDto createPost(Post post) { return postRepository.save(post).toPostResponseDto(); }

    //게시글 수정
    public PostResponseDto updatePost(Post post) { return postRepository.save(post).toPostResponseDto(); }

    //게시글 삭제
    public void deletePost(long id) { postRepository.deleteById(id); }
}
