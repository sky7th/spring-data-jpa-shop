package com.sky7th.springdatajpashop.service;

import com.sky7th.springdatajpashop.domain.Address;
import com.sky7th.springdatajpashop.domain.Member;
import com.sky7th.springdatajpashop.domain.item.Book;
import com.sky7th.springdatajpashop.service.dto.MemberDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

@RequiredArgsConstructor
@Service
public class MockCreateService {

    private final MemberService memberService;
    private final ItemService itemService;
    private final OrderService orderService;

    @PostConstruct
    public void initCreateMock() {
        MemberDto member = MemberDto.builder()
                .name("회원1")
                .address(Address.builder().city("고담").street("굴다리").zipcode("123-123").build())
                .build();

        Long joinedMemberId = memberService.join(member);

        Book book1 = createBook("객체지향의 사실과 오해", 20000, 10);
        Long book1Id = itemService.save(book1);
        Book book2 = createBook("이펙티브 자바", 40000, 20);
        Long book2Id = itemService.save(book2);

        orderService.order(joinedMemberId, book1Id, 5);
    }

    private Book createBook(String name, int price, int stockQuantity) {
        return Book.builder()
                .name(name)
                .price(price)
                .stockQuantity(stockQuantity)
                .build();
    }

}