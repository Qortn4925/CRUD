package com.example.prjjsp20241022.controller;

import com.example.prjjsp20241022.dto.Member;
import com.example.prjjsp20241022.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.Map;

@Controller
@RequiredArgsConstructor
@RequestMapping("member")
public class MemberController {
    private final MemberService service;


    @GetMapping("signup")
    public void signUp(){
    }


//     signup.jsp에서   저장 누를시 작동
    @PostMapping("signup")
    public String signUpProcess(Member member ,RedirectAttributes rttr){
         service.addMember(member);
         rttr.addFlashAttribute("message", Map.of
                 ("type","success" ,"text","회원가입 완료 !")
         );
         return "redirect:/board/list";
    }


    @GetMapping("list")
    public void  abc(Model model){
        List<Member> members = service.viewMember();
        model.addAttribute("memberList", members);
    }

    @GetMapping("view")
    public void info(String id, Model model) {
        Member member = service.info(id);
        model.addAttribute("member", member);
    }

    @PostMapping("delete")
    public String delete(String id, String password, RedirectAttributes rttr) {

        if (service.remove(id, password)) {
            // 탈퇴 성공
            rttr.addFlashAttribute("message", Map.of("type", "dark",
                    "text", "회원 탈퇴하였습니다."));

            return "redirect:/member/signup";
        } else {
            // 탈퇴 실패
            rttr.addFlashAttribute("message", Map.of("type", "danger",
                    "text", "패스워드가 일치하지 않습니다."));
            rttr.addAttribute("id", id);

            return "redirect:/member/view";
        }

    }

    @GetMapping("edit")
    public void edit(String id, Model model) {
        model.addAttribute("member", service.info(id));
    }

    @PostMapping("edit")
    public String editProcess(Member member, RedirectAttributes rttr) {

        try {
            service.update(member);
            rttr.addFlashAttribute("message", Map.of("type", "danger",
                    "text", "회원 정보가 수정 되었스비다"));
        } catch (DuplicateKeyException e) {
            System.out.println("별명 중복!");
            rttr.addFlashAttribute("message", Map.of("type", "danger",
                    "text", "이미 사용중인 별명입니다."));
            return "redirect:/member/edit";
        }

        return "redirect:/member/view";
    }

}
