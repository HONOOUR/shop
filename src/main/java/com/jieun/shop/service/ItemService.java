package com.jieun.shop.service;

import com.jieun.shop.domain.ItemsByCategoryResponse;
import com.jieun.shop.domain.dto.*;
import com.jieun.shop.entity.Brand;
import com.jieun.shop.entity.Category;
import com.jieun.shop.entity.Item;
import com.jieun.shop.repository.BrandRepository;
import com.jieun.shop.repository.CategoryRepository;
import com.jieun.shop.repository.ItemQueryRepository;

import java.util.*;
import java.util.stream.Collectors;

import com.jieun.shop.repository.ItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ItemService {

    private final ItemQueryRepository itemQueryRepository;
    private final CategoryRepository categoryRepository;
    private final BrandRepository brandRepository;
    private final ItemRepository itemRepository;

    public ItemResponse getItemsWithLowestPrice() {
        HashMap<Long, Integer> lowestPricesPerCategory = itemQueryRepository.findLowestPricesPerCategory();
        Map<Long, ItemDto> items = itemQueryRepository.findAll().stream()
                .filter(item -> lowestPricesPerCategory.containsKey(item.getCategoryId())
                        && item.getPrice().equals(lowestPricesPerCategory.get(item.getCategoryId())))
                .sorted(Comparator.comparing(ItemDto::getBrandName).reversed())
                .collect(Collectors.toMap(
                        ItemDto::getCategoryId,
                        item -> item,
                        (existing, replacement) -> existing,
                        LinkedHashMap::new
                ));

        return ItemResponse.builder()
            .items(items.values().stream().sorted(Comparator.comparing(ItemDto::getCategoryId)).toList())
            .totalPrice(String.valueOf(items.values().stream().toList().stream().mapToInt(ItemDto::getPrice).sum()))
            .build();
    }

    public ItemsByBrandResponse getItemsWithLowestTotalPriceByAllCategoriesAndSameBrand() {
        BrandWithTotalPriceDto brandWithTotalPriceDto = itemQueryRepository.findBrandAndLowestPricesTotal();
        List<ItemDto> items = itemQueryRepository.findAllByBrand(brandWithTotalPriceDto.getBrand());

        return ItemsByBrandResponse.builder()
                .itemsByBrand(ItemsByBrandDto.builder()
                        .brandName(brandWithTotalPriceDto.getBrand().getName())
                        .items(items)
                        .totalPrice(brandWithTotalPriceDto.getTotalPrice().toString()).build())
                .build();

    }

    public ItemsByCategoryResponse getLowestAndHighestPriceItem(String categoryName) {
        Category category = categoryRepository.findByName(categoryName).orElseThrow(
                () -> new RuntimeException("카테고리 이름을 찾을 수 없습니다.")
        );

        List<ItemDto> items = itemQueryRepository.findAllByCategory(category);

        return ItemsByCategoryResponse.builder()
                .categoryName(categoryName)
                .itemWithLowestPrice(Collections.singletonList(items.get(0)))
                .itemWithHighestPrice(Collections.singletonList(items.get(items.size()-1))).build();
    }

    public void addItem(ItemRequest itemRequest) {
        if (Objects.isNull(itemRequest.getBrandName()) || Objects.isNull(itemRequest.getCategoryId())) {
            throw new RuntimeException("필수 파라미터가 없습니다.");
        }

        Category category = categoryRepository.findById(itemRequest.getCategoryId()).orElseThrow(
            () -> new RuntimeException("카테고리를 찾을 수 없습니다.")
        );

        Brand brand = brandRepository.findByName(itemRequest.getBrandName()).orElse(null);
        if (Objects.nonNull(brand) && !itemRepository.findAllByCategoryAndBrand(category, brand).isEmpty()) {
                throw new RuntimeException("이미 있는 상품입니다.");
        } else {
            brand = Brand.builder()
                .name(itemRequest.getBrandName())
                .build();
            brandRepository.save(brand);
        }

        itemRepository.save(Item.builder()
                .category(category)
                .brand(brand)
                .price(itemRequest.getPrice()).build());
    }

    public void modify(ItemRequest itemRequest) {
        if (Objects.isNull(itemRequest.getItemId()) && (Objects.isNull(itemRequest.getBrandId()) || Objects.isNull(itemRequest.getCategoryId())
                || Objects.isNull(itemRequest.getPrice()))) {
            throw new RuntimeException("필수 파라미터가 없습니다.");
        }

        Item item = itemRepository.findById(itemRequest.getItemId()).orElseThrow(
            () -> new RuntimeException("존재하지 않는 상품입니다.")
        );

        if (Objects.nonNull(itemRequest.getBrandId())) {
            item.setBrand(brandRepository.findById(itemRequest.getBrandId()).orElseThrow(
                () -> new RuntimeException("브랜드를 찾을 수 없습니다."))
            );
        }

        if (Objects.nonNull(itemRequest.getCategoryId())) {
            item.setCategory(categoryRepository.findById(itemRequest.getCategoryId())
                .orElseThrow(
                    () -> new RuntimeException("카테고리를 찾을 수 없습니다."))
            );
        }

        if (Objects.nonNull(itemRequest.getPrice())) {
            item.setPrice(itemRequest.getPrice());
        }

        itemRepository.save(item);
    }

    public void removeItem(Long itemId) {
        if (Objects.isNull(itemId)) {
            throw new RuntimeException("필수 파라미터가 없습니다.");
        }

        itemRepository.deleteById(itemId);
    }
}