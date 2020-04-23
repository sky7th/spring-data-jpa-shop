package com.sky7th.springdatajpashop.dto.member;

import com.sky7th.springdatajpashop.domain.Member;
import lombok.Getter;

import static com.sky7th.springdatajpashop.utils.LocalDateTimeUtils.toStringDate;

@Getter
public class MemberMainResponseDto {

    private Long id;
    private String name;
    private String city;
    private String street;
    private String zipcode;
    private String createdDate;


    public MemberMainResponseDto(Member entity) {
        this.id = entity.getId();
        this.name = entity.getName();
        this.city = entity.getAddress().getCity();
        this.street = entity.getAddress().getStreet();
        this.zipcode = entity.getAddress().getZipcode();
        this.createdDate = toStringDate(entity.getCreatedDate(), "yyyy-MM-dd");
    }

}
