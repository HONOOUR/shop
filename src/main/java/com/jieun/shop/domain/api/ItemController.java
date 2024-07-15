package com.jieun.shop.domain.api;

import com.jieun.shop.domain.dto.ItemRequest;
import com.jieun.shop.service.ItemService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/items")
@RequiredArgsConstructor
public class ItemController {
    private final ItemService itemService;

    @Operation(description = "카테고리 별 최저가격 브랜드와 상품 가격, 총액을 조회")
    @GetMapping("/category/lowest")
    public ResponseEntity<?> getItemsWithLowestPrice() {
        return ResponseEntity.ok(itemService.getItemsWithLowestPrice());
    }

    @Operation(description = "단일 브랜드로 모든 카테고리 상품을 구매할 때 최저가격에 판매하는 브랜드와 카테고리의 상품가격, 총액을 조회")
    @GetMapping("/brand")
    public ResponseEntity<?> getItemsLowestTotal() {
        return ResponseEntity.ok(itemService.getItemsWithLowestTotalPriceByAllCategoriesAndSameBrand());
    }

    @Operation(description = "카테고리 이름으로 최저, 최고 가격 브랜드와 상품 가격을 조회")
    @GetMapping("/category")
    public ResponseEntity<?> getItemsWithLowestAndHighestPrice(@RequestParam String categoryName) {
        return ResponseEntity.ok(itemService.getLowestAndHighestPriceItem(categoryName));
    }

    @Operation(description = "브랜드 및 상품 추가")
    @PostMapping()
    public ResponseEntity<?> addItem(@RequestBody ItemRequest itemRequest) {
        itemService.addItem(itemRequest);
        return ResponseEntity.ok().build();
    }

    @Operation(description = "브랜드 및 상품 수정")
    @PutMapping()
    public ResponseEntity<?> modifyItem(@RequestBody ItemRequest itemRequest) {
        itemService.modify(itemRequest);
        return ResponseEntity.ok().build();
    }

    @Operation(description = "브랜드 및 상품 삭제")
    @DeleteMapping()
    public ResponseEntity<?> removeItem(@RequestParam Long itemId) {
         itemService.removeItem(itemId);
         return ResponseEntity.ok().build();
    }
}
