package com.sky7th.springdatajpashop.dto;

import com.sky7th.springdatajpashop.domain.item.Book;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@RequiredArgsConstructor
@Setter
@Getter
public class BookDto {

    private Long id;

    private String name;

    private int price;
    private int stockQuantity;

    private String author;
    private String isbn;

    public Book toBook() {
        return Book.builder()
                .name(this.name)
                .price(this.price)
                .stockQuantity(this.stockQuantity)
                .author(this.author)
                .isbn(this.isbn)
                .build();
    }

}
