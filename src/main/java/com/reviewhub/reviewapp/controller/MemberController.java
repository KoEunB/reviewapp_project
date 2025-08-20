package com.reviewhub.reviewapp.controller;

import com.reviewhub.reviewapp.dto.MemberDTO;
import com.reviewhub.reviewapp.service.MemberService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class MemberController {
    //생성자 주입
    private final MemberService memberService;

    //회원가입 페이지 출력 요청
    @GetMapping("/member/save")
    public String saveForm() {
        return "save";
    }

    @PostMapping("/member/save")
    public String save(@ModelAttribute MemberDTO memberDTO) {
        System.out.println("MemberController.save");
        System.out.println("memberDTO = " + memberDTO);
        memberService.save(memberDTO);
        return "login";
    }

    @GetMapping("/member/login")
    public String loginForm() {
        return "login";
    }

    @PostMapping("/member/login")
    public String login(@ModelAttribute MemberDTO memberDTO, HttpSession session, Model model) {
        MemberDTO loginResult = memberService.login(memberDTO);
        if (loginResult != null) {
            // login 성공
            session.setAttribute("loginEmail", loginResult.getEmail());
            session.setAttribute("loginId", loginResult.getId());
            return "redirect:/member/" + loginResult.getId();
        } else {
            // login 실패
            model.addAttribute("loginError", "이메일 또는 비밀번호가 올바르지 않습니다.");
            return "login";
        }
    }

    @GetMapping("/member/{id}")
    public String mypage(@PathVariable Long id, HttpSession session, Model model) {
        String loginEmail = (String) session.getAttribute("loginEmail");
        MemberDTO memberDTO = memberService.updateForm(loginEmail); //이메일로 회원 조회
        // URL의 {id}와 세션의 회원이 일치하지 않으면 접근 차단
        if (!memberDTO.getId().equals(id)) {
            return "redirect:/";
        }
        model.addAttribute("member", memberDTO);
        return "mypage";
    }

    @GetMapping("/member/update")
    public String updateForm(HttpSession session,Model model) {
        String myEmail = (String) session.getAttribute("loginEmail");
        MemberDTO memberDTO = memberService.updateForm(myEmail);
        model.addAttribute("updateMember", memberDTO);
        return "update";
    }

    @PostMapping("/member/update")
    public String update(@ModelAttribute MemberDTO memberDTO) {
        memberService.update(memberDTO);
        return "redirect:/member/" + memberDTO.getId();
    }

    @GetMapping("/member/delete/{id}")
    public String deleteById(@PathVariable Long id) {
        memberService.deleteById(id);
        return "redirect:/member/";
    }

    @GetMapping("/member/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/";
    }

    @PostMapping("/member/email-check")
    public @ResponseBody String emailCheck(@RequestParam("email") String email) {
        System.out.println("email = " + email);
        String checkResult = memberService.emailCheck(email);
        return checkResult;
    }
}
