package com.jieun.shop.domain.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ItemDto {

    String categoryName;

    String brandName;

    Integer price;
}
