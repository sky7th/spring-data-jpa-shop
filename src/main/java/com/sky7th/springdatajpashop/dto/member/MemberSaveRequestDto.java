package com.sky7th.springdatajpashop.dto.member;

import com.sky7th.springdatajpashop.domain.Address;
import com.sky7th.springdatajpashop.domain.Member;
import lombok.*;

@NoArgsConstructor
@Setter
@Getter
public class MemberSaveRequestDto {

    private String name;
    private String city;
    private String street;
    private String zipcode;

    public Member toEntity() {
        return Member.builder()
                .name(this.name)
                .address(Address.builder()
                        .city(this.city)
                        .street(this.street)
                        .zipcode(this.zipcode)
                        .build())
                .build();
    }

}
