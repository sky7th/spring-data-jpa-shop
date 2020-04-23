package com.sky7th.springdatajpashop.web;

import com.sky7th.springdatajpashop.domain.item.ItemType;
import com.sky7th.springdatajpashop.dto.item.ItemMainResponseDto;
import com.sky7th.springdatajpashop.dto.item.ItemSaveRequestDto;
import com.sky7th.springdatajpashop.dto.item.book.BookSaveRequestDto;
import com.sky7th.springdatajpashop.service.ItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.support.SessionStatus;

import java.util.List;

@RequiredArgsConstructor
@Controller
public class ItemController {

    private final ItemService itemService;

    @PostMapping("/items")
    public String create(ItemSaveRequestDto dto) {
        itemService.save(dto.toEntity());

        return "redirect:/";
    }

    @GetMapping("/items/create")
    public String create(Model model) {

        return "items/createItem";
    }

    @GetMapping("/items/update/{id}")
    public String update(@PathVariable(name = "id") Long id, Model model) {
        model.addAttribute("item", itemService.findOne(id));

        return "items/updateItem";
    }

    @PostMapping("/items/update/{id}")
    public String update(@PathVariable(name = "id") Long id, ItemSaveRequestDto dto, SessionStatus status) {
        itemService.update(id, dto);
        status.setComplete();

        return "redirect:/";
    }

    @GetMapping("/items")
    public String findAll(Model model) {
        model.addAttribute("items", itemService.findAll());

        return "items/itemList";
    }

}
