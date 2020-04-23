package com.sky7th.springdatajpashop.service;

import com.sky7th.springdatajpashop.domain.Member;
import com.sky7th.springdatajpashop.repository.MemberRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import static junit.framework.TestCase.fail;
import static org.junit.jupiter.api.Assertions.assertEquals;

@RunWith(SpringRunner.class)
@Transactional
@SpringBootTest
public class MemberServiceTest {

    @Autowired
    MemberService memberService;
    @Autowired
    MemberRepository memberRepository;

    @Test
    public void 회원가입을_한다() throws Exception {

        //Given
        Member member = Member.builder()
                .name("태화").build();

        //When
        Long saveId = memberService.join(member);

        //Then
        assertEquals(member, memberRepository.findById(saveId).orElse(null));
    }

    @Test(expected = IllegalStateException.class)
    public void 중복_회원가입_시_예외를_던진다() throws Exception {

        //Given
        Member member1 = Member.builder()
                .name("태화").build();

        Member member2 = Member.builder()
                .name("태화").build();

        //When
        memberService.join(member1);
        memberService.join(member2); //예외가 발생해야 한다.

        //Then
        fail("예외가 발생해야 한다.");
    }

}
