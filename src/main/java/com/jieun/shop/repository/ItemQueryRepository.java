package com.jieun.shop.repository;

import com.jieun.shop.domain.dto.BrandWithTotalPriceDto;
import com.jieun.shop.domain.dto.ItemDto;

import java.util.HashMap;
import java.util.List;

import com.jieun.shop.entity.Brand;
import com.jieun.shop.entity.Category;
import com.querydsl.core.Tuple;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.Expressions;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import static com.jieun.shop.entity.QBrand.brand;
import static com.jieun.shop.entity.QItem.item;
import static com.jieun.shop.entity.QCategory.category;

@Repository
@RequiredArgsConstructor
public class ItemQueryRepository {

    private final JPAQueryFactory jpaQueryFactory;

    public List<ItemDto> findAll() {
        return jpaQueryFactory.select(Projections.fields(ItemDto.class,
                        category.id.as("categoryId"),
                        category.name.as("categoryName"),
                        brand.name.as("brandName"),
                        item.price,
                        Expressions.stringTemplate("CAST({0} AS STRING)", item.price).as("priceText")
                ))
                .from(item)
                .join(brand).on(brand.eq(item.brand))
                .join(category).on(category.eq(item.category))
                .orderBy(category.id.asc())
                .fetch();
    }

    public HashMap<Long, Integer> findLowestPricesPerCategory() {
        List<Tuple> categoryPrice = jpaQueryFactory
                .select(
                        category.id,
                        item.price.min())
                .from(item)
                .groupBy(category)
                .fetch();
        HashMap<Long, Integer> categoryToPriceMap = new HashMap<>();
        categoryPrice.forEach(tuple -> categoryToPriceMap.put(tuple.get(category.id), tuple.get(item.price.min())));
        return categoryToPriceMap;
    }

    public BrandWithTotalPriceDto findBrandAndLowestPricesTotal() {
        return jpaQueryFactory.select(Projections.fields(BrandWithTotalPriceDto.class,
                        item.brand,
                        item.price.sum().as("totalPrice")
                ))
                .from(item)
                .groupBy(item.brand)
                .orderBy(item.price.sum().asc())
                .limit(1)
                .fetchOne();
    }

    public List<ItemDto> findAllByBrand(Brand brand) {
        return jpaQueryFactory.select(Projections.fields(ItemDto.class,
                        category.name.as("categoryName"),
                        item.price,
                        Expressions.stringTemplate("CAST({0} AS STRING)", item.price).as("priceText")
                ))
                .from(item)
                .join(category).on(item.category.eq(category))
                .where(item.brand.eq(brand))
                .fetch();
    }

    public List<ItemDto> findAllByCategory(Category category) {
        return jpaQueryFactory.select(Projections.fields(ItemDto.class,
                        brand.name.as("brandName"),
                        item.price,
                        Expressions.stringTemplate("CAST({0} AS STRING)", item.price).as("priceText")
                ))
                .from(item)
                .join(brand).on(item.brand.eq(brand))
                .where(item.category.eq(category))
                .orderBy(item.price.asc())
                .fetch();
    }
}
