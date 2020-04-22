package com.sky7th.springdatajpashop.domain;

import lombok.Data;

import javax.persistence.Embeddable;

@Data
@Embeddable
public class Address {

    private String city;
    private String street;
    private String zipcode;

}
