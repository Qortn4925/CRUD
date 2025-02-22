package com.example.prjjsp20241022.controller;

import com.example.prjjsp20241022.dto.Member;
import com.example.prjjsp20241022.service.MemberService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
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
    public String  abc(Model model ,
                       @SessionAttribute(value = "loggedInMember",required = false)
                       Member member,RedirectAttributes rttr){

            if( member !=null && member.getAuth().contains("admin")) {
                List<Member> members = service.viewMember();
                model.addAttribute("memberList", members);
                return  null;
            }else {
                rttr.addFlashAttribute("message", Map.of
                        ("type", "warning", "text", "로그인 한 사용자만 이용하실 수 있습니다.")
                );
                return "redirect:/member/login";
            }

    }

    @GetMapping("view")
    public void info(String id, Model model) {
        Member member = service.info(id);
        model.addAttribute("member", member);
    }

    @PostMapping("delete")
    public String delete(String id,
                         String password,
                         HttpSession session,
                         RedirectAttributes rttr,
                         @SessionAttribute("loggedInMember") Member member) {

        if (service.hasAccess(id, member)) {
            if (service.remove(id, password)) {
                // 탈퇴 성공
                rttr.addFlashAttribute("message", Map.of("type", "dark",
                        "text", "회원 탈퇴하였습니다."));

                session.invalidate();
                return "redirect:/member/signup";
            } else {
                // 탈퇴 실패
                rttr.addFlashAttribute("message", Map.of("type", "danger",
                        "text", "패스워드가 일치하지 않습니다."));
                rttr.addAttribute("id", id);

                return "redirect:/member/view";
            }
        } else {
            rttr.addFlashAttribute("message", Map.of("type", "danger",
                    "text", "권한이 없습니다."));

            session.invalidate();
            return "redirect:/member/login";
        }

    }


    @GetMapping("edit")
    public String edit(String id, Model model,
                       RedirectAttributes rttr,
                       @SessionAttribute("loggedInMember") Member member) {

        if (service.hasAccess(id, member)) {
            model.addAttribute("member", service.info(id));
            return null;
        } else {
            rttr.addFlashAttribute("message", Map.of("type", "danger",
                    "text", "권한이 없습니다."));
            return "redirect:/member/login";
        }
    }

    @PostMapping("edit")
    public String editProcess(Member member, RedirectAttributes rttr,
                              @SessionAttribute("loggedInMember") Member loggedInMember) {
        if (service.hasAccess(member.getId(), loggedInMember)) {
            try {
                service.update(member);
                rttr.addFlashAttribute("message", Map.of("type", "success",
                        "text", "회원정보가 수정되었습니다."));

            } catch (DuplicateKeyException e) {
                rttr.addFlashAttribute("message", Map.of("type", "danger",
                        "text", STR."\{member.getNickName()}은 이미 사용중인 별명입니다."));

                rttr.addAttribute("id", member.getId());
                return "redirect:/member/edit";
            }

            rttr.addAttribute("id", member.getId());
            return "redirect:/member/view";
        } else {

            rttr.addFlashAttribute("message", Map.of("type", "danger",
                    "text", "권한이 없습니다."));
            return "redirect:/member/login";
        }
    }

    @GetMapping("edit-password")
    public String editPassword(String id, Model model) {
        model.addAttribute("id", id);

        return "/member/editPassword";
    }

    @PostMapping("edit-password")
    public String editPasswordProcess(String id,
                                      String oldPassword,
                                      String newPassword,
                                      RedirectAttributes rttr) {
        if (service.updatePassword(id, oldPassword, newPassword)) {
            rttr.addFlashAttribute("message", Map.of("type", "success",
                    "text", "암호가 변경되었습니다."));
            rttr.addAttribute("id", id);
            return "redirect:/member/view";
        } else {
            rttr.addFlashAttribute("message", Map.of("type", "warning",
                    "text", "암호가 변경되지 않았습니다."));
            rttr.addAttribute("id", id);
            return "redirect:/member/edit-password";
        }

    }
    @GetMapping("login")
    public void login() {

    }

    @PostMapping("login")
    public String loginProcess(String id, String password, RedirectAttributes rttr, HttpSession session) {

        Member member=service.get(id,password);
        if(member==null){
            rttr.addFlashAttribute("message", Map.of
                    ("type","warning","text","일치하는 아이디 패스워드가 없습니다")
            );
            return "redirect:/member/login";
        }
        else {

            rttr.addFlashAttribute("message", Map.of
                    ("type","success","text","로그인 성공")
            );
            session.setAttribute("loggedInMember",member);
            return "redirect:/board/list";
        }
    }

    @RequestMapping("logout")
    public String logout(HttpSession session,RedirectAttributes rttr) {
        // 세션에 들어가있던 거 다지움
        session.invalidate();

        rttr.addFlashAttribute("message", Map.of
                ("type","success","text","로그아웃 되었습니다.")
        );

        return "redirect:/member/login";

    }
}
