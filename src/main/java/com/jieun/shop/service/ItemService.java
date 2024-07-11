package com.jieun.shop.service;

import com.jieun.shop.domain.dto.ItemDto;
import com.jieun.shop.domain.dto.ItemResponse;
import com.jieun.shop.repository.ItemQueryRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ItemService {


    private final ItemQueryRepository itemQueryRepository;

    public ItemResponse getItemsWithLowestPrice() {
        List<ItemDto> items = itemQueryRepository.findByLowestPriceInCategories();

        return ItemResponse.builder()
            .items(items)
            .totalPrice(items.stream().mapToInt(ItemDto::getPrice).sum())
            .build();
    }
}
