package com.sky7th.springdatajpashop.web;

import com.sky7th.springdatajpashop.dto.item.ItemSaveRequestDto;
import com.sky7th.springdatajpashop.service.ItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.support.SessionStatus;

@RequiredArgsConstructor
@Controller
public class ItemController {

    private final ItemService itemService;

    @PostMapping("/items")
    public String create(ItemSaveRequestDto dto) {
        itemService.save(dto.toEntity());

        return "redirect:/items";
    }

    @GetMapping("/items/create")
    public String create() {

        return "items/createItem";
    }

    @GetMapping("/items/update/{id}")
    public String update(@PathVariable(name = "id") Long id, Model model) {
        model.addAttribute("item", itemService.findOne(id));

        return "items/updateItem";
    }

    @PostMapping("/items/{id}")
    public String update(@PathVariable(name = "id") Long id, ItemSaveRequestDto dto, SessionStatus status) {
        itemService.update(id, dto);
        status.setComplete();

        return "redirect:/items";
    }

    @GetMapping("/items")
    public String findAll(Model model) {
        model.addAttribute("items", itemService.findAll());

        return "items/itemList";
    }

}
