package com.sky7th.springdatajpashop.dto.item;

import com.sky7th.springdatajpashop.domain.item.*;
import lombok.Getter;

import static com.sky7th.springdatajpashop.utils.LocalDateTimeUtils.toStringDate;

@Getter
public class ItemMainResponseDto {

    private Long id;
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

    private String createdDate;

    public ItemMainResponseDto(Item entity) {
        this.id = entity.getId();
        this.itemType = entity.getDecriminatorValue();
        this.name = entity.getName();
        this.price = entity.getPrice();
        this.stockQuantity = entity.getStockQuantity();
        this.createdDate = toStringDate(entity.getCreatedDate(), "yyyy-MM-dd");

        if (this.itemType.equalsIgnoreCase(ItemType.ALBUM.getName())) {
            Album album = (Album) entity;
            this.artist = album.getArtist();
            this.etc = album.getEtc();

        } else if (this.itemType.equalsIgnoreCase(ItemType.BOOK.getName())) {
            Book book = (Book) entity;
            this.author = book.getAuthor();
            this.isbn = book.getIsbn();

        } else if (this.itemType.equalsIgnoreCase(ItemType.MOVIE.getName())) {
            Movie movie = (Movie) entity;
            this.actor = movie.getActor();
            this.director = movie.getDirector();
        }
    }

}
