package com.sky7th.springdatajpashop.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping("/")
    public String home() {
        return "home";
    }

    @GetMapping("/member")
    public String member() {
        return "member";
    }

    @GetMapping("/item")
    public String item() {
        return "item";
    }

    @GetMapping("/order")
    public String order() {
        return "order";
    }

}
