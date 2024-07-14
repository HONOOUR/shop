package com.jieun.shop.domain.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Getter
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ItemDto {

    Long categoryId;

    @JsonProperty("카테고리")
    String categoryName;

    @JsonProperty("브랜드")
    String brandName;

    @JsonIgnore
    Integer price;

    @JsonProperty("가격")
    String priceText;

    @Builder
    public ItemDto(Long categoryId, String categoryName, String brandName, Integer price) {
        this.categoryId = categoryId;
        this.categoryName = categoryName;
        this.brandName = brandName;
        this.priceText = price.toString();
        this.price = price;
    }
}
