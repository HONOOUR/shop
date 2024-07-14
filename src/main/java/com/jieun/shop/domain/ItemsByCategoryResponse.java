package com.jieun.shop.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.jieun.shop.domain.dto.ItemDto;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@Builder
public class ItemsByCategoryResponse {

    @JsonProperty("카테고리")
    String categoryName;

    @JsonProperty("최저가")
    List<ItemDto> itemWithLowestPrice;

    @JsonProperty("최고가")
    List<ItemDto> itemWithHighestPrice;

}
