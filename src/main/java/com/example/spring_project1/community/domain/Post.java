package com.example.spring_project1.community.domain;

import com.example.spring_project1.community.domain.Board.Entity.Board;
import jakarta.persistence.Column;
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
    private Board board;

    @Column(length = 20, nullable = false)
    private String title;

    @Column(columnDefinition = "Text")
    private String content;

    @Column(length = 20, nullable = false)
    private String location;

    private int like_count;
}
