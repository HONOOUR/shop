package com.jieun.shop.domain.api;

import com.jieun.shop.domain.dto.ItemRequest;
import com.jieun.shop.service.ItemService;
import lombok.RequiredArgsConstructor;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/items")
@RequiredArgsConstructor
public class ItemController {
    private final ItemService itemService;

    @GetMapping("/lowest")
    public ResponseEntity<?> getItemsWithLowestPrice() {
        return ResponseEntity.ok(itemService.getItemsWithLowestPrice());
    }

    @GetMapping("/brand")
    public ResponseEntity<?> getItemsLowestTotal() {
        return ResponseEntity.ok(itemService.getItemsWithLowestTotalPriceByAllCategoriesAndSameBrand());
    }

    @GetMapping("/category")
    public ResponseEntity<?> getItemsWithLowestAndHighestPrice(@RequestParam String categoryName) {
        return ResponseEntity.ok(itemService.getLowestAndHighestPriceItem(categoryName));
    }

    @PostMapping()
    public ResponseEntity<?> addItem(@RequestBody ItemRequest itemRequest) {
        itemService.addItem(itemRequest);
        return ResponseEntity.ok().build();
    }

    @PutMapping()
    public ResponseEntity<?> modifyItem(@RequestBody ItemRequest itemRequest) {
        itemService.modify(itemRequest);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping()
    public ResponseEntity<?> removeItem(@RequestParam Long itemId) {
         itemService.removeItem(itemId);
         return ResponseEntity.ok().build();
    }
}
