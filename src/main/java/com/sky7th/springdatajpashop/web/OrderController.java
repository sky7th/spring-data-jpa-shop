package com.sky7th.springdatajpashop.web;

import com.sky7th.springdatajpashop.dto.item.ItemMainResponseDto;
import com.sky7th.springdatajpashop.dto.member.MemberMainResponseDto;
import com.sky7th.springdatajpashop.dto.order.OrderMainResponseDto;
import com.sky7th.springdatajpashop.dto.order.OrderSearch;
import com.sky7th.springdatajpashop.service.ItemService;
import com.sky7th.springdatajpashop.service.MemberService;
import com.sky7th.springdatajpashop.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@Controller
public class OrderController {

    private final OrderService orderService;
    private final MemberService memberService;
    private final ItemService itemService;

    @GetMapping("/orders/create")
    public String createForm(Model model) {

        List<MemberMainResponseDto> members = memberService.findAll();
        List<ItemMainResponseDto> items = itemService.findAll();

        model.addAttribute("members", members);
        model.addAttribute("items", items);

        return "orders/createOrder";
    }

    @PostMapping("/orders")
    public String order(@RequestParam("memberId") Long memberId, @RequestParam("itemId") Long itemId, @RequestParam("count") int count) {

        orderService.order(memberId, itemId, count);
        return "redirect:/orders";
    }

    @GetMapping("/orders")
    public String orderList(OrderSearch orderSearch, Model model) {
        List<OrderMainResponseDto> orders = orderService.findAll(orderSearch);

        model.addAttribute("orderSearch", orderSearch);
        model.addAttribute("orders", orders);

        return "orders/orderList";
    }

    @DeleteMapping(value = "/orders/{orderId}")
    public Long processCancelBuy(@PathVariable("orderId") Long orderId) {

        return orderService.cancelOrder(orderId);
    }

}
