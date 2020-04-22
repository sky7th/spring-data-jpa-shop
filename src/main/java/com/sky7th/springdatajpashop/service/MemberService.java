package com.sky7th.springdatajpashop.service;

import com.sky7th.springdatajpashop.domain.Member;
import com.sky7th.springdatajpashop.repository.MemberRepository;
import com.sky7th.springdatajpashop.service.dto.MemberDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class MemberService {

    private final MemberRepository memberRepository;

    public Long join(MemberDto memberDto) {
        validateDuplicateMember(memberDto);
        Member member = memberRepository.save(memberDto.toMember());
        return member.getId();
    }

    private void validateDuplicateMember(MemberDto memberDto) {
        List<Member> findMembers = memberRepository.findAllByName(memberDto.getName());
        if (!findMembers.isEmpty()) {
            throw new IllegalStateException("이미 존재하는 회원입니다.");
        }
    }

}
