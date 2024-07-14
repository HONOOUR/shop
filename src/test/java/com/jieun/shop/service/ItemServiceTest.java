package com.jieun.shop.service;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

import com.jieun.shop.domain.dto.ItemDto;
import com.jieun.shop.repository.ItemQueryRepository;

import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;


@SpringBootTest
@ActiveProfiles("test")
@ExtendWith(SpringExtension.class)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class ItemServiceTest {
    @Autowired
    private ItemService itemService;

    @Test
    @DisplayName("카테고리 중복 테스트")
    void none_duplication_in_categories() {
        List<ItemDto> itemsByBrand = itemService.getItemsWithLowestTotalPriceByAllCategoriesAndSameBrand()
                .getItemsByBrand().getItems();
        Assertions.assertEquals(itemsByBrand.size(),
        itemsByBrand.stream().map(ItemDto::getCategoryName).collect(Collectors.toSet()).stream().count());
    }
}