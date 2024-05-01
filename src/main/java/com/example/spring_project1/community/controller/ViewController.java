package com.example.spring_project1.community.controller;

import com.example.spring_project1.community.domain.Board.Dto.BoardPostDto;
import com.example.spring_project1.community.domain.Board.Dto.BoardResponseDto;
import com.example.spring_project1.community.domain.Board.Dto.BoardUpdateDto;
import com.example.spring_project1.community.domain.Board.Entity.Board;
import com.example.spring_project1.community.service.BoardService;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/boards")
public class ViewController {

    private final BoardService boardService;

    public ViewController(BoardService boardService) {
        this.boardService = boardService;
    }


    //게시판 목록 (기본페이지)
    @GetMapping
    public String getAllBoardView(Model model) {
        List<BoardResponseDto> boards = boardService.findBoards();
        model.addAttribute("boardlist", boards);
        return "boards";
    }



    // localhost/createboard 로 넘어가면 게시판 생성하는 페이지
    @GetMapping("/createboard")
    public String getCreateBoardView() {
        return "categorycreate";
    }


    //특정 게시판 id 조회
    @GetMapping("/{boardid}")
    public ResponseEntity<?> getBoardById(@PathVariable("boardid") long boardid, Model model) {
        Optional<BoardResponseDto> board = Optional.ofNullable(boardService.findBoard(boardid));
        if (!board.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Invalid board ID: " + boardid);
        }
        return ResponseEntity.ok(board);
    }

    //특정 게시판 수정하는 페이지
    @GetMapping("/edit/{boardid}")
    public String editBoardView(@PathVariable("boardid") long boardid, Model model)
        throws IllegalAccessException {
        Optional<BoardResponseDto> board = Optional.ofNullable(boardService.findBoard(boardid));
        //.해당 id를 가진 board가 존재하지 않으면
        if (!board.isPresent()) {
            throw new IllegalAccessException("invalid board" + boardid);
        }
        model.addAttribute("board", board.get());
        return "categoryedit";
    }


    //게시판 생성하기 (생성 페이지에서 제출 시)
    @PostMapping("/createboard")
    public String createBoard(@RequestParam String title, @RequestParam String pw, @RequestParam String sub_title, Model model) {
        BoardPostDto boardPostDto = new BoardPostDto(pw, title, sub_title, LocalDateTime.now(), null);
        BoardResponseDto board= boardService.createBoard(boardPostDto.toEntity());
        return "redirect:/boards";
    }

    @PostMapping("/edit/{boardid}")
    public String editBoard(@PathVariable("boardid") long boardid, @RequestParam String title, @RequestParam String pw, @RequestParam String sub_title) {
        LocalDateTime createdAt = boardService.findBoard(boardid).getCreatedAt();
        BoardUpdateDto boardUpdateDto = new BoardUpdateDto(boardid, pw, title, sub_title, createdAt, LocalDateTime.now());
        boardService.updateBoard(boardUpdateDto.toEntity());

        return "redirect:/boards";
    }

    @GetMapping("/delete/{boardid}")
    public String deleteBoard(@PathVariable("boardid") long boardid) {
        boardService.deleteBoard(boardid);
        return "redirect:/boards";
    }
}


