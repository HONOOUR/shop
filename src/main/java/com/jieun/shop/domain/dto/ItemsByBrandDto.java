package com.jieun.shop.domain.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@Builder
public class ItemsByBrandDto {

    @JsonProperty("브랜드")
    String brandName;

    @JsonProperty("카테고리")
    List<ItemDto> items;

    @JsonProperty("총액")
    String totalPrice;
}
