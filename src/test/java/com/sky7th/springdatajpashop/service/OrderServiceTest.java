package com.sky7th.springdatajpashop.service;

import com.sky7th.springdatajpashop.domain.Address;
import com.sky7th.springdatajpashop.domain.Member;
import com.sky7th.springdatajpashop.domain.Order;
import com.sky7th.springdatajpashop.domain.OrderStatus;
import com.sky7th.springdatajpashop.domain.item.Book;
import com.sky7th.springdatajpashop.domain.item.Item;
import com.sky7th.springdatajpashop.exception.NotEnoughStockException;
import com.sky7th.springdatajpashop.repository.order.OrderRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import static junit.framework.TestCase.fail;
import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@Transactional
@SpringBootTest
public class OrderServiceTest {

    @PersistenceContext
    EntityManager em;

    @Autowired
    OrderService orderService;
    @Autowired
    OrderRepository orderRepository;

    @Test
    public void 상품을_주문한다() throws Exception {

        //Given
        Member member = createMember("태화", "서울", "어느도로", "123-123");
        Item item = createBook("jpa 책", 20000, 10);
        int orderCount = 2;

        //When
        Long orderId = orderService.order(member.getId(), item.getId(), orderCount);

        //Then
        Order getOrder = orderRepository.findById(orderId).orElse(null);

        assertEquals("상품 주문시 상태는 주문(ORDER)이다.", OrderStatus.ORDER, getOrder.getStatus());
        assertEquals("주문한 상품 종류 수가 정확해야 한다.", 1, getOrder.getOrderItems().size());
        assertEquals("주문 가격은 가격 * 수량이다.", 20000 * orderCount, getOrder.getTotalPrice());
        assertEquals("주문 수량만큼 재고가 줄어야 한다.",8, item.getStockQuantity());
    }

    @Test(expected = NotEnoughStockException.class)
    public void 상품주문이_재고수량을_초과한다() throws Exception {

        //Given
        Member member = createMember("태화", "서울", "어느도로", "123-123");
        Item item = createBook("jpa 책", 20000, 10);
        int orderCount = 11; //재고 보다 많은 수량

        //When
        orderService.order(member.getId(), item.getId(), orderCount);

        //Then
        fail("재고 수량 부족 예외가 발생해야 한다.");
    }

    @Test
    public void 주문을_취소한다() {

        //Given
        Member member = createMember("태화", "서울", "어느도로", "123-123");
        Item item = createBook("jpa 책", 20000, 10);
        int orderCount = 2;

        Long orderId = orderService.order(member.getId(), item.getId(), orderCount);

        //When
        orderService.cancelOrder(orderId);

        //Then
        Order getOrder = orderRepository.findById(orderId).orElse(null);

        assertEquals("주문 취소시 상태는 CANCEL 이다.", OrderStatus.CANCEL, getOrder.getStatus());
        assertEquals("주문이 취소된 상품은 그만큼 재고가 증가해야 한다.", 10, item.getStockQuantity());
    }

    private Member createMember(String name, String city, String street, String zipcode) {
        Member member = Member.builder()
                .name(name)
                .address(Address.builder().city(city).street(street).zipcode(zipcode).build())
                .build();

        em.persist(member);
        return member;
    }

    private Book createBook(String name, int price, int stockQuantity) {
        Book book = Book.builder()
                .name(name)
                .stockQuantity(stockQuantity)
                .price(price)
                .build();

        em.persist(book);
        return book;
    }

}
