package com.jieun.shop.repository;

import com.jieun.shop.domain.dto.ItemDto;
import java.util.List;
import org.springframework.stereotype.Repository;

@Repository
public class ItemQueryRepository {

    public List<ItemDto> findByLowestPriceInCategories() {
        return null;
    }
}
