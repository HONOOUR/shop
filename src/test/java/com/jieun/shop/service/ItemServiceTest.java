package com.jieun.shop.service;

import static org.mockito.Mockito.when;

import com.jieun.shop.domain.dto.ItemDto;
import com.jieun.shop.domain.dto.ItemResponse;
import com.jieun.shop.entity.Brand;
import com.jieun.shop.entity.Category;
import com.jieun.shop.entity.Item;
import com.jieun.shop.repository.ItemQueryRepository;
import com.jieun.shop.repository.ItemRepository;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.mock.mockito.MockBean;

@ExtendWith(MockitoExtension.class)
class ItemServiceTest {

    @InjectMocks
    ItemService itemService;

    @Mock
    ItemRepository itemRepository;

    @Mock
    ItemQueryRepository itemQueryRepository;

//    카테고리 별 최저가격 브랜드와 상품 가격(item), 총액을 sum(price) 조회하는 API
    @Test
    void sum_of_min_price_in_each_category_equal_total_price() {
        // given
        List<ItemDto> items = new ArrayList<>();
        items.add(ItemDto.builder()
                .categoryName("상의")
                .brandName("C")
                .price(10000).build());
        items.add(ItemDto.builder()
            .categoryName("아우터")
            .brandName("E")
            .price(5000).build());

        // when
        when(itemQueryRepository.findByLowestPriceInCategories()).thenReturn(items);

        // then
        ItemResponse itemResponse = itemService.getItemsWithLowestPrice();
        Integer totalPrice = itemResponse.getItems().stream().mapToInt(ItemDto::getPrice).sum();
        Assertions.assertEquals(totalPrice, itemResponse.getTotalPrice());
    }
}