package com.example.spring_project1.community.controller;

import com.example.spring_project1.community.domain.Board.Dto.BoardResponseDto;
import com.example.spring_project1.community.domain.Board.Entity.Board;
import com.example.spring_project1.community.service.BoardService;
import com.example.spring_project1.community.controller.BoardForm;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import javax.swing.text.html.Option;
import org.springframework.cglib.core.Local;
import org.springframework.ui.Model;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("")
public class ViewController {

    private final BoardService boardService;

    public ViewController(BoardService boardService) {
        this.boardService = boardService;
    }


    //게시판 목록 (기본페이지)
    @GetMapping("/kimnarim")
    public String getAllBoardView(Model model) {
        //board 찾기
        List<Board> boards = boardService.findBoards();
        //model에 board 추가
        model.addAttribute("boards", boards);
        return "boards";
    }

    // localhost/createboard 로 넘어가면 게시판 생성하는 페이지
    // 버튼을 누르는 동작은 -?
    @GetMapping("/createboard")
    public String getCreateBoardView() {
        return "boardcreate";
    }

    //특정 게시판 들어가기
    @GetMapping("/{boardid}")
    public String getBoardById(@PathVariable long boardid, Model model)
        throws IllegalAccessException {
        Optional<Board> board = Optional.ofNullable(boardService.findBoard(boardid));
        //.해당 id를 가진 board가 존재하지 않으면
        if (!board.isPresent()) {
            throw new IllegalAccessException("invalid board" + boardid);
        }
        model.addAttribute("board", board.get());
        return "board2";
    }

    //특정 게시판 수정하는 페이지
//    @GetMapping("/edit/{boardid}")
//    public String editBoardView(@PathVariable long boardid, Model model)
//        throws IllegalAccessException {
//        Optional<Board> board = Optional.ofNullable(boardService.findBoard(boardid));
//        //.해당 id를 가진 board가 존재하지 않으면
//        if (!board.isPresent()) {
//            throw new IllegalAccessException("invalid board" + boardid);
//        }
//        model.addAttribute("board", board.get());
//        return "boardedit.html";
//    }

    //게시판 생성하기 (생성 페이지에서 제출 시)
    @PostMapping("/createboard")
    public String createBoard(@ModelAttribute BoardForm form) {
        Board board = new Board();
        //form에서 가져온 data 설정
        board.setPw(form.getPw());
        board.setTitle(form.getTitle());
        board.setSub_title(form.getSub_title());
        board.setCreatedAt(LocalDateTime.now());
        //board 생성
        board = boardService.createBoard(board);
        //이전 페이지로 돌아가기
        return "redirect:/" + board.getId();
    }

//    @PutMapping("/edit/{boardid}")
//    public String updateBoard(@ModelAttribute BoardForm form) {
//        Board board = new Board();
//        board.setPw(form.getPw());
//        board.setTitle(form.getTitle());
//        board.setSub_title(form.getSub_title());
//        board.setModifiedAt(LocalDateTime.now());
//        board = boardService.updateBoard(board);
//        return "redirect:/" + board.getId();
//    }

    @DeleteMapping("/{boardid}")
    public String deleteBoard(@PathVariable long boardid) {
        boardService.deleteBoard(boardid);
        return "redirect:/";
    }
}


