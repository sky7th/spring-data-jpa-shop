package com.sky7th.springdatajpashop.dto.order;

import com.sky7th.springdatajpashop.domain.OrderStatus;
import lombok.*;

@NoArgsConstructor
@Getter
@Setter
public class OrderSearch {

    private String memberName;
    private OrderStatus orderStatus;

    @Builder
    public OrderSearch(String memberName, OrderStatus orderStatus) {
        this.memberName = memberName;
        this.orderStatus = orderStatus;
    }
}