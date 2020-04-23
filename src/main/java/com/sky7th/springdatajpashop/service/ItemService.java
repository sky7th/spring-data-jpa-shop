package com.sky7th.springdatajpashop.service;

import com.sky7th.springdatajpashop.domain.item.Item;
import com.sky7th.springdatajpashop.dto.item.ItemMainResponseDto;
import com.sky7th.springdatajpashop.dto.item.ItemSaveRequestDto;
import com.sky7th.springdatajpashop.repository.ItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class ItemService {

    private final ItemRepository itemRepository;

    @Transactional
    public Long save(Item item) {
        Item savedItem = itemRepository.save(item);
        return savedItem.getId();
    }

    public List<ItemMainResponseDto> findAll() {
        return itemRepository.findAll().stream()
                .map(ItemMainResponseDto::new)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public ItemMainResponseDto findOne(Long itemId) {
        return new ItemMainResponseDto(itemRepository.findById(itemId)
                .orElseThrow(() -> new IllegalStateException("해당 item이 존재하지 않습니다. id=" + itemId)));
    }

    @Transactional
    public Long update(Long itemId, ItemSaveRequestDto dto) {
        Item item = itemRepository.findById(itemId)
                .orElseThrow(() -> new IllegalStateException("해당 item이 존재하지 않습니다. id=" + itemId));
        item.update(dto);

        return item.getId();
    }

}
