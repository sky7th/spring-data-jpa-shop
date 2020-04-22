package com.sky7th.springdatajpashop.service.dto;

import com.sky7th.springdatajpashop.domain.OrderStatus;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public class OrderSearch {

    private String memberName;
    private OrderStatus orderStatus;

    @Builder
    public OrderSearch(String memberName, OrderStatus orderStatus) {
        this.memberName = memberName;
        this.orderStatus = orderStatus;
    }
}