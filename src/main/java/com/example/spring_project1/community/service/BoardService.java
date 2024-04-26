package com.example.spring_project1.community.service;

import com.example.spring_project1.community.domain.Board.Entity.Board;
import com.example.spring_project1.community.repository.JdbcTemplateBoardRepository;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class BoardService {
    private final JdbcTemplateBoardRepository boardRepository;

    public BoardService(JdbcTemplateBoardRepository boardRepository) {
        this.boardRepository = boardRepository;
    }
    //게시판 조회
    public List<Board> findBoards() {
        return boardRepository.findAll();
    }

    public Board findBoard(long id) {
        return boardRepository.findById(id).orElseThrow(() -> new RuntimeException());
    }

    //게시판 생성
    public Board createBoard(Board board) {
        return boardRepository.save(board);
    }

    //게시판 수정
    public Board updateBoard(Board board) { return boardRepository.save(board);}
    public void deleteBoard(long id) {
        Board board = boardRepository.findById(id).orElseThrow(() -> new RuntimeException());
        boardRepository.delete(board);
    }

}
