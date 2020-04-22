package com.sky7th.springdatajpashop.repository.order;

import com.sky7th.springdatajpashop.domain.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long>, OrderRepositoryCustom {
}
