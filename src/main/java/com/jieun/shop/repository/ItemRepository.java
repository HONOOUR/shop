package com.jieun.shop.repository;

import com.jieun.shop.entity.Brand;
import com.jieun.shop.entity.Category;
import com.jieun.shop.entity.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ItemRepository extends JpaRepository<Item, Long> {

    List<Item> findAllByCategoryAndBrand(Category category, Brand brand);
}
