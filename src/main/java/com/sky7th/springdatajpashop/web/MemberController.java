package com.sky7th.springdatajpashop.web;

import com.sky7th.springdatajpashop.dto.member.MemberSaveRequestDto;
import com.sky7th.springdatajpashop.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@RequiredArgsConstructor
@Controller
public class MemberController {

    private final MemberService memberService;

    @PostMapping("/members")
    public String join(MemberSaveRequestDto dto) {
        memberService.join(dto.toEntity());

        return "redirect:/";
    }

    @GetMapping("/members/join")
    public String join(Model model) {

        return "members/joinMember";
    }

    @GetMapping("/members")
    public String findAll(Model model) {
        model.addAttribute("members", memberService.findAll());

        return "members/memberList";
    }

}
