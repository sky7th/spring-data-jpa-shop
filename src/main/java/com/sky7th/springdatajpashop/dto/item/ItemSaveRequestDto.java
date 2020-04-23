package com.sky7th.springdatajpashop.dto.item;

import com.sky7th.springdatajpashop.domain.item.*;
import lombok.Getter;
import lombok.Setter;

import static com.sky7th.springdatajpashop.utils.LocalDateTimeUtils.toStringDate;

@Getter
@Setter
public class ItemSaveRequestDto {

    private String itemType;
    private String name;
    private int price;
    private int stockQuantity;
    private String artist;
    private String etc;
    private String author;
    private String isbn;
    private String director;
    private String actor;

    public Item toEntity() {
        if (this.itemType.equals(ItemType.ALBUM.getName()))
            return toAlbumEntity();

        else if (this.itemType.equals(ItemType.BOOK.getName()))
            return toBookEntity();

        else if (this.itemType.equals(ItemType.MOVIE.getName()))
            return toMovieEntity();

        return null;
    }

    public Album toAlbumEntity() {
        return Album.builder()
                .name(this.name)
                .price(this.price)
                .stockQuantity(this.stockQuantity)
                .artist(this.artist)
                .etc(this.etc)
                .build();
    }

    public Book toBookEntity() {
        return Book.builder()
                .name(this.name)
                .price(this.price)
                .stockQuantity(this.stockQuantity)
                .author(this.author)
                .isbn(this.isbn)
                .build();
    }

    public Movie toMovieEntity() {
        return Movie.builder()
                .name(this.name)
                .price(this.price)
                .stockQuantity(this.stockQuantity)
                .director(this.director)
                .actor(this.actor)
                .build();
    }

}
