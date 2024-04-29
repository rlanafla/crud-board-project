//package com.example.spring_project1.community.controller;
//
//import com.example.spring_project1.community.domain.Board.Entity.Board;
//import com.example.spring_project1.community.domain.Board.Dto.BoardPostDto;
//import com.example.spring_project1.community.domain.Board.Dto.BoardResponseDto;
//import com.example.spring_project1.community.service.BoardService;
//import io.swagger.v3.oas.annotations.Operation;
//import java.util.List;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.DeleteMapping;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//@RestController
//@Controller
////API만 구현
//@RequestMapping("/boards")
//public class BoardController {
//    private final BoardService boardService;
//    //객체 생성
//    @Autowired
//    public BoardController(BoardService boardservice) {
//        this.boardService = boardservice;
//    }
//
//    //게시판 들어가기
//    @GetMapping("/{boardid}")
//    public ResponseEntity<Board> getBoard(@PathVariable long boardid) {
//        Board board = boardService.findBoard(boardid);
//            //해당 id를 가진 board가 없다면
//            if (board == null) {
//                return new ResponseEntity<>(board, HttpStatus.NOT_FOUND);
//            }
//            return new ResponseEntity<>(board, HttpStatus.OK);
//    }
//
//    //모든 게시판 조회
//    @GetMapping
//    public ResponseEntity<Board> getBoards() {
//        List<Board> boards = boardService.findBoards();
//            if (boards.isEmpty()) {
//                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//            }
//            return new ResponseEntity<>(HttpStatus.OK);
//    }
//
//    @Operation(summary = "게시판 등록", description = "유저가 게시판을 등록합니다.")
//    @PostMapping
//    //pw와 title, 등등을 받아서
//    //id와 함께 반환한다.
//    public ResponseEntity<BoardResponseDto> postBoard(@RequestBody BoardPostDto boardPostDto) {
//        Board board = boardPostDto.toEntity();
//        Board newBoard = boardService.createBoard(board);
//
//        BoardResponseDto boardResponseDto = newBoard.toBoardResponseDto();
//        return new ResponseEntity<>(boardResponseDto, HttpStatus.CREATED);
//    }
//
//    @Operation(summary = "게시판 수정", description =  "유저가 게시판을 수정합니다")
//    @PostMapping("/{boardid}")
//    public ResponseEntity<BoardResponseDto> updateBoard(@RequestBody BoardPostDto boardPostDto) {
//        Board board = boardPostDto.toEntity();
//        Board newBoard = boardService.updateBoard(board);
//
//        BoardResponseDto boardResponseDto = newBoard.toBoardResponseDto();
//        return new ResponseEntity<>(boardResponseDto, HttpStatus.OK);
//    }
//
//    //delete
//    @DeleteMapping("/{boardid}")
//    public ResponseEntity<Void> deleteBoard(@PathVariable long boardid) {
//        boardService.deleteBoard(boardid);
//        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
//    }
//
//}
//
