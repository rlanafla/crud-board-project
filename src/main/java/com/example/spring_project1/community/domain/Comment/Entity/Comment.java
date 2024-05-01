package com.example.spring_project1.community.domain.Comment.Entity;

import com.example.spring_project1.community.domain.Comment.Dto.CommentResponseDto;
import com.example.spring_project1.community.domain.Post.Dto.PostResponseDto;
import com.example.spring_project1.community.domain.Post.Entity.BaseEntity;
import com.example.spring_project1.community.domain.Post.Entity.Post;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Getter
@Setter
@Table(name = "comment")
public class Comment extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 10, nullable = false)
    private String pw;

    @ManyToOne
    @JoinColumn(name="post_id")
    private Post post;

    @Column(columnDefinition = "Text")
    private String content;

    public CommentResponseDto tocommentResponseDto() {
        Comment comment = new Comment();
        return new CommentResponseDto(id, pw, content, comment.getCreatedAt(), comment.getModifiedAt());
    }
}
