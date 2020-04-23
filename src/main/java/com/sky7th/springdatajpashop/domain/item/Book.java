package com.sky7th.springdatajpashop.domain.item;

import com.sky7th.springdatajpashop.dto.item.ItemSaveRequestDto;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Getter
@NoArgsConstructor
@Entity
@DiscriminatorValue("BOOK")
public class Book extends Item {

    private String author;
    private String isbn;

    @Builder
    public Book(String name, int price, int stockQuantity, String author, String isbn) {
        super(name, price, stockQuantity);
        this.author = author;
        this.isbn = isbn;
    }

    public void update(ItemSaveRequestDto dto) {
        super.update(dto);
        this.author = dto.getAuthor();
        this.isbn = dto.getIsbn();
    }
}
