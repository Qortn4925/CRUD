package com.example.prjjsp20241022.controller;


import com.example.prjjsp20241022.dto.Board;
import com.example.prjjsp20241022.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.Map;

@Controller
@RequiredArgsConstructor
@RequestMapping("/board")
public class BoardController {

    private  final BoardService service;

    @GetMapping("new")
    public void newBoard(){}

    @PostMapping("new")
    public String newBoard(Board board ,RedirectAttributes rttr){
        service.add(board);
        System.out.println(board);

        rttr.addFlashAttribute("message", Map.of("type","sucess" ,
                "text" ,"새 게시글이 등록 되었습니다") );

        rttr.addAttribute("id", board.getId());

            // 이거 안하면 포스트 방식이라 새로고침 하면 테이블 계속 들어감

        return "redirect:/board/view";
    }

    @GetMapping("list")
    public void listBoard(Model model , @RequestParam(name ="page",defaultValue = "1") Integer page){

        Map<String,Object> result = service.list(page);
        model.addAllAttributes(result);



    }

    @GetMapping("view")
    public void viewBoard(Integer id , Model model){
       Board board= service.get(id);
        model.addAttribute("board",board);
    }

    @PostMapping("delete")
    public String deleteBoard(Integer id , RedirectAttributes rttr ){
        service.remove(id);

        rttr.addFlashAttribute("message", Map.of("type","sucess" ,
                "text" ,"새 게시글이 등록 되었습니다") );
        return "redirect:/board/list";
    }
    @GetMapping("edit")
    public void editBoard(Integer id , Model model){
        model.addAttribute("board",service.get(id));
    }

    @PostMapping("edit")
    public String editBoard(Board board, RedirectAttributes rttr){
        service.update(board);
        System.out.println(board);
        rttr.addFlashAttribute("message", Map.of("type","sucess" ,
                "text" ,"새 게시글이 등록 되었습니다") );
        rttr.addAttribute("id",board.getId());
        return  "redirect:/board/view";
    }
}
