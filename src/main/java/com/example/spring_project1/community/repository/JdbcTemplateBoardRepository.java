package com.example.spring_project1.community.repository;


import com.example.spring_project1.community.domain.Board.Dto.BoardResponseDto;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.*;
import com.example.spring_project1.community.domain.Board.Entity.Board;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

//게시판 목록 CRUD 구현
@Repository
public class JdbcTemplateBoardRepository {
    private final JdbcTemplate jdbcTemplate;
    @Autowired
    public JdbcTemplateBoardRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
    //게시판 생성
    public BoardResponseDto save(Board board) {
        //새로운 board를 저장하는 경우 새 id 생성
        String sql = "insert into board(pw, title, sub_title, createdAt, modifiedAt) values(?, ?, ?, ?, ?)";
        if (board.getId() == null) {
            KeyHolder keyHolder = new GeneratedKeyHolder();
            jdbcTemplate.update(
                connection -> {
                    PreparedStatement ps = connection.prepareStatement(
                        sql, Statement.RETURN_GENERATED_KEYS
                    );
                    //db에 값 저장
                    ps.setObject(1, board.getPw());
                    ps.setObject(2, board.getTitle());
                    ps.setObject(3, board.getSub_title());
                    ps.setObject(4, board.getCreatedAt());
                    ps.setObject(5, board.getModifiedAt());
                    return ps;
                }, keyHolder
            );
            //생성된 key를 가져옴
            Number key = keyHolder.getKey();
            if (key != null) {
                //key가 유효하면 id로 설정
                board.setId(key.longValue());
            } else {
                //key가 유효하지 않으면
                throw new RuntimeException("key generate failed");
            }
        }
        return board.toBoardResponseDto();
    }

    public BoardResponseDto update(Board board){
        String sql = "update board set pw = ?, title = ?, sub_title = ?, createdAt = ?, modifiedAt = ? where id = ?";
        jdbcTemplate.update(sql, board.getPw(), board.getTitle(), board.getSub_title(), board.getCreatedAt(), board.getModifiedAt(), board.getId());
        return board.toBoardResponseDto();
    }
    //게시판 조회
    private RowMapper<BoardResponseDto> boardRowMapper() {
        return (rs, rowNum) -> {
            Board board = new Board();
            board.setId(rs.getLong("id"));
            board.setPw(rs.getString("pw"));
            board.setTitle(rs.getString("title"));
            board.setSub_title(rs.getString("sub_title"));

            Timestamp createdAtTimestamp = rs.getTimestamp("createdAt");
            if (createdAtTimestamp != null) {
                board.setCreatedAt(createdAtTimestamp.toLocalDateTime());
            }

            Timestamp modifiedAtTimestamp = rs.getTimestamp("modifiedAt");
            if (modifiedAtTimestamp != null) {
                board.setModifiedAt(modifiedAtTimestamp.toLocalDateTime());
            }

            return board.toBoardResponseDto();
        };
    }
    public List<BoardResponseDto> findAll() {
        return jdbcTemplate.query("select  * from board", boardRowMapper());
    }

    public Optional<BoardResponseDto> findById(long id) {
        try {
            BoardResponseDto board = jdbcTemplate.queryForObject(
                "select * from board where id = ?", boardRowMapper(), id
            );
            return Optional.ofNullable(board);
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }
    }

    //게시판 삭제
    public void delete(BoardResponseDto board) {
        String sql = "delete from board where id = ?";
        jdbcTemplate.update(sql, board.getId());
    }
}
