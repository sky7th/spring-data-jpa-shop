package com.sky7th.springdatajpashop.service;

import com.sky7th.springdatajpashop.domain.Delivery;
import com.sky7th.springdatajpashop.domain.Member;
import com.sky7th.springdatajpashop.domain.Order;
import com.sky7th.springdatajpashop.domain.OrderItem;
import com.sky7th.springdatajpashop.domain.item.Item;
import com.sky7th.springdatajpashop.repository.ItemRepository;
import com.sky7th.springdatajpashop.repository.MemberRepository;
import com.sky7th.springdatajpashop.repository.order.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class OrderService {

    private final MemberRepository memberRepository;
    private final OrderRepository orderRepository;
    private final ItemRepository itemRepository;

    @Transactional
    public Long order(Long memberId, Long itemId, int count) {
        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new IllegalStateException("해당 member가 존재하지 않습니다. id=" + memberId));
        Item item = itemRepository.findById(itemId)
                .orElseThrow(() -> new IllegalStateException("해당 item이 존재하지 않습니다. id=" + itemId));

        Delivery delivery = Delivery.builder().address(member.getAddress()).build();
        OrderItem orderItem = OrderItem.createOrderItem(item, item.getPrice(), count);
        Order order = Order.createOrder(member, delivery, orderItem);

        orderRepository.save(order);

        return order.getId();
    }

    @Transactional
    public void cancelOrder(Long orderId) {
        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new IllegalStateException("해당 order가 존재하지 않습니다. id=" + orderId));

        order.cancel();
    }

}
