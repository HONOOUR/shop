package com.jieun.shop.domain.dto;


import lombok.AccessLevel;
import lombok.Getter;
import lombok.experimental.FieldDefaults;

@Getter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ItemRequest {

    Long itemId;

    Long categoryId;

    Long brandId;

    String brandName;

    Integer price;
}
