package com.jieun.shop.domain.dto;

import java.util.List;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ItemResponse {

    List<ItemDto> items;

    String totalPrice;
}
