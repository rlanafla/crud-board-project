package com.example.spring_project1.community.domain.Post.Entity;

import com.example.spring_project1.community.domain.Board.Dto.BoardResponseDto;
import com.example.spring_project1.community.domain.Comment.Entity.Comment;
import com.example.spring_project1.community.domain.Post.Dto.PostResponseDto;
import com.example.spring_project1.community.domain.Post.Entity.BaseEntity;
import com.example.spring_project1.community.domain.Board.Entity.Board;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Table(name = "post")
public class Post extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 10, nullable = false)
    private String pw;

    //외래키 : board table의 id를 참조
    //@JoinColumn(name="대상table_대상table의columnname")
    //대상 table 객체
    @ManyToOne
    @JoinColumn(name="board_id")
    private Board board_id;

    @Column(length = 20, nullable = false)
    private String title;

    @Column(columnDefinition = "Text")
    private String content;

    @Column(length = 20, nullable = false)
    private String location;

    private int like_count;

    @OneToMany(mappedBy = "post", cascade = CascadeType.ALL)
    private List<Comment> commentList = new ArrayList<>();

    public PostResponseDto toPostResponseDto() {
        return new PostResponseDto(id, pw, title, content, location, like_count, getCreated_at(), getModified_at());
    }

}
