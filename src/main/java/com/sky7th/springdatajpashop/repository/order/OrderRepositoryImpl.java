package com.sky7th.springdatajpashop.repository.order;

import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.sky7th.springdatajpashop.domain.Order;
import lombok.RequiredArgsConstructor;
import org.springframework.util.StringUtils;

import java.util.List;

import static com.sky7th.springdatajpashop.domain.QOrder.order;

@RequiredArgsConstructor
public class OrderRepositoryImpl implements OrderRepositoryCustom {

    private final JPAQueryFactory queryFactory;

    @Override
    public List<Order> search(OrderSearch orderSearch) {
        return queryFactory.selectFrom(order)
                .leftJoin(order.member).fetchJoin()
                .where(equalOrderStatus(orderSearch), containName(orderSearch))
                .fetch();
    }

    private BooleanExpression containName(OrderSearch orderSearch) {
        if (!StringUtils.hasText(orderSearch.getMemberName())) {
            return null;
        }
        return order.member.name.contains(orderSearch.getMemberName());
    }

    private BooleanExpression equalOrderStatus(OrderSearch orderSearch) {
        if (orderSearch.getOrderStatus() == null) {
            return null;
        }
        return order.status.eq(orderSearch.getOrderStatus());
    }
}
