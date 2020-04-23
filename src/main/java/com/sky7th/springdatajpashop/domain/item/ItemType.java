package com.sky7th.springdatajpashop.domain.item;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum ItemType {

    ALBUM("album"),
    BOOK("book"),
    MOVIE("movie");

    private final String name;

    public String getName() {
        return name;
    }

}
