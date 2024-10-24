package com.example.prjjsp20241022.controller;

import com.example.prjjsp20241022.dto.Member;
import com.example.prjjsp20241022.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Map;

@Controller
@RequiredArgsConstructor
@RequestMapping("member")
public class MemberController {
    private final MemberService service;

    @GetMapping("signup")
    public void signUp(){

    }

    @PostMapping("signup")
    public String signUpProcess(Member member ,RedirectAttributes rttr){
         service.addMember(member);
         rttr.addFlashAttribute("message", Map.of
                 ("type","success" ,"text","회원가입 완료 !")
         );

         return "redirect:/board/list";
    }

}
