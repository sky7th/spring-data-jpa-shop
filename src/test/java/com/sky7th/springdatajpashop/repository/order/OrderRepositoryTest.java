package com.sky7th.springdatajpashop.repository.order;

import com.sky7th.springdatajpashop.domain.Member;
import com.sky7th.springdatajpashop.domain.Order;
import com.sky7th.springdatajpashop.domain.OrderStatus;
import com.sky7th.springdatajpashop.domain.item.Book;
import com.sky7th.springdatajpashop.repository.order.OrderRepository;
import com.sky7th.springdatajpashop.service.ItemService;
import com.sky7th.springdatajpashop.service.MemberService;
import com.sky7th.springdatajpashop.service.OrderService;
import com.sky7th.springdatajpashop.service.dto.MemberDto;
import com.sky7th.springdatajpashop.service.dto.OrderSearch;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class OrderRepositoryTest {

    @Autowired
    MemberService memberService;
    @Autowired
    ItemService itemService;
    @Autowired
    OrderService orderService;
    @Autowired
    OrderRepository orderRepository;

    @Test
    public void test() throws Exception {

        //Given
        MemberDto memberDto = createMember("태화");
        Book book = createItem("꿀잼책", 10);
        orderService.order(memberDto.getId(), book.getId(), 1);

        //When
        OrderSearch orderSearch = OrderSearch.builder()
                .memberName("hello")
                .orderStatus(OrderStatus.ORDER)
                .build();

        List<Order> search = orderRepository.search(orderSearch);
        search.forEach(v -> {
            System.out.println(v.getMember().getName()+" "+v.getOrderItems().get(0).getItem().getName());
        });

        //Then
        Assert.assertEquals(1, search.size());
    }

    private MemberDto createMember(String name) {
        MemberDto memberDto = MemberDto.builder().name(name).build();
        Long memberId = memberService.join(memberDto);
        return MemberDto.builder().id(memberId).name(name).build();
    }

    private Book createItem(String name, int stockQuantity) {
        Book book = Book.builder()
                .name(name)
                .stockQuantity(stockQuantity).build();

        itemService.save(book);
        return book;
    }

}
