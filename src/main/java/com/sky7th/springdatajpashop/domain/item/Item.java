package com.sky7th.springdatajpashop.domain.item;

import com.sky7th.springdatajpashop.domain.Category;
import com.sky7th.springdatajpashop.domain.common.BaseTimeEntity;
import com.sky7th.springdatajpashop.dto.item.ItemSaveRequestDto;
import com.sky7th.springdatajpashop.exception.NotEnoughStockException;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor
@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "ITEM_TYPE")
public abstract class Item extends BaseTimeEntity {

    @Id @GeneratedValue
    @Column(name = "ITEM_ID")
    private Long id;

    private String name;
    private int price;
    private int stockQuantity;

    public Item(String name, int price, int stockQuantity) {
        this.name = name;
        this.price = price;
        this.stockQuantity = stockQuantity;
    }

    @ManyToMany(mappedBy = "items")
    private List<Category> categories = new ArrayList<>();

    public void addStock(int orderQuantity) {
        this.stockQuantity += orderQuantity;
    }

    public void removeStock(int orderQuantity) {
        int restStock = this.stockQuantity - orderQuantity;
        if (restStock < 0) {
            throw new NotEnoughStockException("need more stock!");
        }
        this.stockQuantity = restStock;
    }

    public void update(ItemSaveRequestDto dto) {
        this.name = dto.getName();
        this.price = dto.getPrice();
        this.stockQuantity = dto.getStockQuantity();
    }

    @Transient
    public String getDecriminatorValue() {
        return this.getClass().getAnnotation(DiscriminatorValue.class).value();
    }

}
