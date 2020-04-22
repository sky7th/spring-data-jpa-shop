package com.sky7th.springdatajpashop.repository.order;

import com.sky7th.springdatajpashop.domain.Order;
import com.sky7th.springdatajpashop.service.dto.OrderSearch;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderRepositoryCustom {

    List<Order> search(OrderSearch orderSearch);

}
