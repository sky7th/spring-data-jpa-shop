package com.sky7th.springdatajpashop.dto.item.book;

import com.sky7th.springdatajpashop.domain.item.Book;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class BookSaveRequestDto {

    private String name;
    private int price;
    private int stockQuantity;
    private String author;
    private String isbn;

    public Book toEntity() {
        return Book.builder()
                .price(this.price)
                .stockQuantity(this.stockQuantity)
                .author(this.author)
                .isbn(this.isbn)
                .build();
    }

}
