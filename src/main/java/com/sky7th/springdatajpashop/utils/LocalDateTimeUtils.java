package com.sky7th.springdatajpashop.utils;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

public class LocalDateTimeUtils {

    public static String toStringDate(LocalDateTime localDateTime, String pattern) {
        if (localDateTime == null) {
            return "";
        }
        return localDateTime.format(DateTimeFormatter.ofPattern(pattern));
    }

    private String toStringDateTime(LocalDateTime localDateTime){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        return Optional.ofNullable(localDateTime)
                .map(formatter::format)
                .orElse("");
    }

}
