package com.sky7th.springdatajpashop.repository.order;

import com.sky7th.springdatajpashop.domain.Order;
import com.sky7th.springdatajpashop.dto.order.OrderSearch;

import java.util.List;

public interface OrderRepositoryCustom {

    List<Order> search(OrderSearch orderSearch);

}
