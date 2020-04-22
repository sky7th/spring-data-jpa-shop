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
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RunWith(SpringRunner.class)
@Transactional
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
        Member member = createMember("태화");
        Book book = createItem("꿀잼책", 10);
        orderService.order(member.getId(), book.getId(), 1);

        //When
        OrderSearch orderSearch = OrderSearch.builder()
                .memberName("태화")
                .orderStatus(OrderStatus.ORDER)
                .build();

        List<Order> search = orderRepository.search(orderSearch);

        //Then
        Assert.assertEquals(1, search.size());
    }

    private Member createMember(String name) {
        Member member = Member.builder().name(name).build();
        memberService.join(member);
        return member;
    }

    private Book createItem(String name, int stockQuantity) {
        Book book = Book.builder()
                .name(name)
                .stockQuantity(stockQuantity).build();

        itemService.save(book);
        return book;
    }

}
