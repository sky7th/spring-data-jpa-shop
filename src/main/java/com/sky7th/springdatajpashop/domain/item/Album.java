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
@DiscriminatorValue("ALBUM")
public class Album extends Item {

    private String artist;
    private String etc;

    @Builder
    public Album(String name, int price, int stockQuantity, String artist, String etc) {
        super(name, price, stockQuantity);
        this.artist = artist;
        this.etc = etc;
    }

    public void update(ItemSaveRequestDto dto) {
        super.update(dto);
        this.artist = dto.getArtist();
        this.etc = dto.getEtc();
    }
}
