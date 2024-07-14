package com.jieun.shop.service;

import com.jieun.shop.domain.ItemsByCategoryResponse;
import com.jieun.shop.domain.dto.ItemDto;
import com.jieun.shop.domain.dto.ItemResponse;
import com.jieun.shop.entity.Category;
import com.jieun.shop.repository.CategoryRepository;
import com.jieun.shop.repository.ItemQueryRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ItemServiceMockTest {

    @InjectMocks
    ItemService itemService;

    @Mock
    CategoryRepository categoryRepository;

    @Mock
    ItemQueryRepository itemQueryRepository;

    @Test
    @DisplayName("최저가 상품가격의 합과 total price 필드 값 동일한지 성공 테스트")
    void sum_of_min_price_in_each_category_equal_total_price_succeeded() {
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

         HashMap<Long, Integer> categoryToLowestPrice = new HashMap<>();

        // when
        when(itemQueryRepository.findAll()).thenReturn(items);
        when(itemQueryRepository.findLowestPricesPerCategory()).thenReturn(categoryToLowestPrice);

        // then
        ItemResponse itemResponse = itemService.getItemsWithLowestPrice();
        Integer totalPrice = itemResponse.getItems().stream()
                .mapToInt(item -> Integer.valueOf(item.getPrice())).sum();
        Assertions.assertEquals(totalPrice, itemResponse.getTotalPrice());
    }

    @Test
    @DisplayName("최저가 가격이 최고가보다 작은지 성공 테스트")
    void lowest_price_is_lower_than_highest_succeeded() {
        // given
        List<ItemDto> items = new ArrayList<>();
        items.add(ItemDto.builder()
                .categoryName("상의")
                .brandName("C")
                .price(10000).build());
        items.add(ItemDto.builder()
                .categoryName("상의")
                .brandName("I")
                .price(11400).build());

        Category category = Category.builder()
                .name("상의")
                .build();

        // when
        when(categoryRepository.findByName(category.getName())).thenReturn(Optional.of(category));
        when(itemQueryRepository.findAllByCategory(category)).thenReturn(items);

        // then
        ItemsByCategoryResponse itemsByCategory = itemService.getLowestAndHighestPriceItem(category.getName());
        Assertions.assertTrue(Integer.valueOf(itemsByCategory.getItemWithLowestPrice().get(0).getPrice())
                <= Integer.valueOf(itemsByCategory.getItemWithHighestPrice().get(0).getPrice()));
    }

    @Test
    @DisplayName("잘못된 카테고리 이름으로 찾기 실패 테스트")
    void wrong_category_name_search_failed() {
        // given
        String categoryName = "원피스";

        // when
        RuntimeException exception = Assertions.assertThrows(RuntimeException.class,
                () -> itemService.getLowestAndHighestPriceItem(categoryName));

        // then
        Assertions.assertEquals(exception.getMessage(), "카테고리 이름을 찾을 수 없습니다.");
    }
}