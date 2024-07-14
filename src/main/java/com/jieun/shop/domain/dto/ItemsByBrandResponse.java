package com.jieun.shop.domain.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ItemsByBrandResponse {

    @JsonProperty("최저가")
    ItemsByBrandDto itemsByBrand;

}
