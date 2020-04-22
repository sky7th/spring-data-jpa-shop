package com.sky7th.springdatajpashop.repository;

import com.sky7th.springdatajpashop.domain.item.Item;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemRepository extends JpaRepository<Item, Long> {
}
