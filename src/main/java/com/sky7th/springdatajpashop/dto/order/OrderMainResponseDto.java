package com.sky7th.springdatajpashop.dto.order;

import com.sky7th.springdatajpashop.domain.Order;
import lombok.Getter;

import static com.sky7th.springdatajpashop.utils.LocalDateTimeUtils.toStringDate;

@Getter
public class OrderMainResponseDto {

    private Long id;
    private String memberName;
    private String firstItemName;
    private int totalPrice;
    private String orderStatus;
    private String orderDate;

    public OrderMainResponseDto(Order entity) {
        this.id = entity.getId();
        this.memberName = entity.getMember().getName();
        this.firstItemName = entity.getOrderItems().get(0).getItem().getName();
        this.totalPrice = entity.getTotalPrice();
        this.orderStatus = entity.getStatus().toString();
        this.orderDate = toStringDate(entity.getCreatedDate(), "yyyy-MM-dd");
    }

}
