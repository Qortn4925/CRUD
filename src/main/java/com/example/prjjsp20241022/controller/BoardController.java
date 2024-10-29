package com.example.prjjsp20241022.controller;


import com.example.prjjsp20241022.dto.Board;
import com.example.prjjsp20241022.dto.Member;
import com.example.prjjsp20241022.service.BoardService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.Map;

@Controller
@RequiredArgsConstructor
@RequestMapping("/board")
public class BoardController {

    private  final BoardService service;

    @GetMapping("new")
    public String newBoard(@SessionAttribute(value = "loggedInMember",required = false) Member member,RedirectAttributes rttr) {
//        Object o = session.getAttribute("loggedInMember");
//        Member m= (Member) o;  >>  @SessionAttribute
        if(member == null){
        // 로그인 안한 상태

            rttr.addFlashAttribute("message", Map.of("type","success" ,
                    "text" ,"로그인한 사용자만 이용가능합니다.") );
            return "redirect:/member/login";

        }else {
            //로그인 한 상태
            return "board/new";
        }
    }

    @PostMapping("new")
    public String newBoard(@SessionAttribute(value = "loggedInMember",required = false) Member member,Board board ,RedirectAttributes rttr){
        service.add(board,member);
        rttr.addFlashAttribute("message", Map.of("type","success" ,
                "text" ,"새 게시글이 등록 되었습니다") );

        rttr.addAttribute("id", board.getId());

            // 이거 안하면 포스트 방식이라 새로고침 하면 테이블 계속 들어감

        return "redirect:/board/view";
    }

    @GetMapping("list")
    public void listBoard(Model model , @RequestParam(name ="page",defaultValue = "1") Integer page
    ,  @RequestParam(required = false)  String searchTarget,
                          @RequestParam(defaultValue = "") String keyword){

        Map<String,Object> result = service.list(page,searchTarget,keyword);
        model.addAllAttributes(result);
    }

    @GetMapping("view")
    public void viewBoard(Integer id , Model model){
       Board board= service.get(id);
        model.addAttribute("board",board);
    }

    @PostMapping("delete")
    public String deleteBoard(Integer id , RedirectAttributes rttr
        ,@SessionAttribute("loggedInMember") Member member)     {
        try{
        service.remove(id,member);

        rttr.addFlashAttribute("message", Map.of("type","success" ,
                "text" ,"게시글 삭제 되었습니다") );
        return "redirect:/board/list";
        } catch(RuntimeException e){
            rttr.addFlashAttribute("message", Map.of("type","success" ,
                    "text" ,"게시글 삭제중 문제가 발생 되었습니다") );
            rttr.addAttribute("id", id);
            return "redirect:/board/view";
        }
    }


    @GetMapping("edit")
    public void editBoard(Integer id , Model model){
        model.addAttribute("board",service.get(id));
    }


    @PostMapping("edit")
    public String editBoard(Board board, RedirectAttributes rttr
    ,@SessionAttribute("loggedInMember") Member member
    ){
        try {
            service.update(board, member);
            rttr.addFlashAttribute("message", Map.of("type", "success",
                    "text", "게시글 수정 완료."));
            rttr.addAttribute("id", board.getId());
            return "redirect:/board/view";
        }catch (RuntimeException e){
            //
            rttr.addFlashAttribute("message", Map.of("type", "danger",
                    "text", board.getId()+"게시글 수정중 문제가 발생"));
            return "redirect:/board/view";
        }
    }


}
