package com.example.spring_project1.community.repository;


import java.sql.PreparedStatement;
import java.sql.Statement;
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
    public Board save(Board board) {
        //새로운 board를 저장하는 경우 새 id 생성
        String sql = "insert into board(pw, title, sub_title, createdAt, modifiedAt) values(?, ?, ?, ?, ?)";
        String sql2 = "update board set pw = ?, title = ?, sub_title = ?, modifiedAt = ? where id = ?";
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
        //board가 이미 존재하는 경우 (createtime은 수정 안함)
        else {
            jdbcTemplate.update(sql2, board.getPw(), board.getTitle(), board.getSub_title(), board.getModifiedAt(), board.getId());
        }
        return board;
    }

    //게시판 수정
//    public int update(Board board){
//        String sql = "update board set pw = ?, title = ?, sub_title = ?, modifiedAt = ? where id = ?";
//        int result = jdbcTemplate.update(sql, board.getPw(), board.getTitle(), board.getSub_title(), board.getModifiedAt(), board.getId());
//        return result;
//    }
    //업데이트 된 행의 수 반환

    //게시판 조회
    private RowMapper<Board> boardRowMapper() {
        return (rs, rowNum) -> {
            //db에서 값 불러옴
            Board board = new Board();
            board.setId(rs.getLong("id"));
            board.setPw(rs.getString("pw"));
            board.setTitle(rs.getString("title"));
            board.setSub_title(rs.getString("sub_title"));
            board.setCreatedAt(rs.getTimestamp("createdAt").toLocalDateTime());
            board.setModifiedAt(rs.getTimestamp("modifiedAt").toLocalDateTime());

            return board;
        };
    }
    public List<Board> findAll() {
        return jdbcTemplate.query("select  * from board", boardRowMapper());
    }

    public Optional<Board> findById(long id) {
        try {
            Board board = jdbcTemplate.queryForObject(
                "select * from board where id = ?", boardRowMapper(), id
            );
            return Optional.ofNullable(board);
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }
    }

    //게시판 삭제
    public void delete(Board board) {
        String sql = "delete from board where id = ?";
        jdbcTemplate.update(sql, board.getId());
    }
}
