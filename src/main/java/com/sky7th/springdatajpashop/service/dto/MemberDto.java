package com.sky7th.springdatajpashop.service.dto;

import com.sky7th.springdatajpashop.domain.Address;
import com.sky7th.springdatajpashop.domain.Member;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public class MemberDto {

    private Long id;

    private String name;

    private Address address;

    @Builder
    public MemberDto(Long id, String name, Address address) {
        this.id = id;
        this.name = name;
        this.address = address;
    }

    public MemberDto(Member member) {
        this.id = member.getId();
        this.name = member.getName();
        this.address = member.getAddress();
    }

    public Member toMember() {
        return Member.builder()
                .name(this.name)
                .address(this.address)
                .build();
    }

}
