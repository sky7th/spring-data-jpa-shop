package com.sky7th.springdatajpashop.domain;

import lombok.*;

import javax.persistence.Embeddable;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Builder
@Embeddable
public class Address {

    private String city;
    private String street;
    private String zipcode;

}
