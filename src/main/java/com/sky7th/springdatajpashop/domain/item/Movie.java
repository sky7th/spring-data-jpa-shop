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
@DiscriminatorValue("MOVIE")
public class Movie extends Item {

    private String director;
    private String actor;

    @Builder
    public Movie(String name, int price, int stockQuantity, String director, String actor) {
        super(name, price, stockQuantity);
        this.director = director;
        this.actor = actor;
    }

    public void update(ItemSaveRequestDto dto) {
        super.update(dto);
        this.director = dto.getDirector();
        this.actor = dto.getActor();
    }
}
