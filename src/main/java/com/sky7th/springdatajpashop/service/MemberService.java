package com.sky7th.springdatajpashop.service;

import com.sky7th.springdatajpashop.domain.Member;
import com.sky7th.springdatajpashop.dto.member.MemberMainResponseDto;
import com.sky7th.springdatajpashop.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class MemberService {

    private final MemberRepository memberRepository;

    @Transactional(readOnly = true)
    public List<MemberMainResponseDto> findAll() {
        return memberRepository.findAll().stream()
                .map(MemberMainResponseDto::new)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public List<MemberMainResponseDto> findByName(String name) {
        return memberRepository.findAllByName(name).stream()
                .map(MemberMainResponseDto::new)
                .collect(Collectors.toList());
    }

    @Transactional
    public Long join(Member member) {
        validateDuplicateMember(member);
        memberRepository.save(member);
        return member.getId();
    }

    private void validateDuplicateMember(Member member) {
        List<Member> findMembers = memberRepository.findAllByName(member.getName());
        if (!findMembers.isEmpty()) {
            throw new IllegalStateException("이미 존재하는 회원입니다.");
        }
    }

}
