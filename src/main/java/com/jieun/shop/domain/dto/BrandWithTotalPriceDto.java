package com.jieun.shop.domain.dto;

import com.jieun.shop.entity.Brand;
import lombok.*;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BrandWithTotalPriceDto {

    Brand brand;

    Integer totalPrice;
}
