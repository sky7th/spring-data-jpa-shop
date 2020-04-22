package com.sky7th.springdatajpashop.service;

import com.sky7th.springdatajpashop.domain.item.Item;
import com.sky7th.springdatajpashop.repository.ItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class ItemService {

    private final ItemRepository itemRepository;

    public Long save(Item item) {
        Item savedItem = itemRepository.save(item);
        return savedItem.getId();
    }

    public List<Item> findAll() {
        return itemRepository.findAll();
    }

    public Item findOne(Long itemId) {
        return itemRepository.findById(itemId).orElseThrow(() -> new IllegalStateException("해당 item이 존재하지 않습니다. id=" + itemId));
    }

}
