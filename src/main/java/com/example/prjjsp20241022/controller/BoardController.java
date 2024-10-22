package com.example.prjjsp20241022.controller;


import com.example.prjjsp20241022.dto.Board;
import com.example.prjjsp20241022.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/board")
public class BoardController {

    private  final BoardService service;

    @GetMapping("new")
    public void newBoard(){}

    @PostMapping("new")
    public String newBoard(Board board){
        service.add(board);
        System.out.println(board);

            // 이거 안하면 포스트 방식이라 새로고침 하면 테이블 계속 들어감
        return "redirect:/board/list";
    }

    @GetMapping("list")
    public void listBoard(Model model){
        List<Board> list = service.list();
        model.addAttribute("boardList",list);
    }

    @GetMapping("view")
    public void viewBoard(Integer id , Model model){
       Board board= service.get(id);
        model.addAttribute("board",board);
    }

}
