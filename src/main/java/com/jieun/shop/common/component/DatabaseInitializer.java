package com.jieun.shop.common.component;

import com.jieun.shop.entity.Brand;
import com.jieun.shop.entity.Category;
import com.jieun.shop.entity.Item;
import com.jieun.shop.repository.BrandRepository;
import com.jieun.shop.repository.CategoryRepository;
import com.jieun.shop.repository.ItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DatabaseInitializer implements CommandLineRunner {

    private final BrandRepository brandRepository;

    private final CategoryRepository categoryRepository;

    private final ItemRepository itemRepository;

    @Override
    public void run(String... args) throws Exception {
        Brand brandA = brandRepository.save(Brand.builder().name("A").build());
        Brand brandB = brandRepository.save(Brand.builder().name("B").build());
        Brand brandC = brandRepository.save(Brand.builder().name("C").build());
        Brand brandD = brandRepository.save(Brand.builder().name("D").build());
        Brand brandE = brandRepository.save(Brand.builder().name("E").build());
        Brand brandF = brandRepository.save(Brand.builder().name("F").build());
        Brand brandG = brandRepository.save(Brand.builder().name("G").build());
        Brand brandH = brandRepository.save(Brand.builder().name("H").build());
        Brand brandI = brandRepository.save(Brand.builder().name("I").build());

        Category category1 = categoryRepository.save(Category.builder().name("상의").build());
        Category category2 = categoryRepository.save(Category.builder().name("아우터").build());
        Category category3 = categoryRepository.save(Category.builder().name("바지").build());
        Category category4 = categoryRepository.save(Category.builder().name("스니커즈").build());
        Category category5 = categoryRepository.save(Category.builder().name("가방").build());
        Category category6 = categoryRepository.save(Category.builder().name("모자").build());
        Category category7 = categoryRepository.save(Category.builder().name("양말").build());
        Category category8 = categoryRepository.save(Category.builder().name("액세서리").build());

        itemRepository.save(Item.builder().brand(brandA).category(category1).price(11200).build());
        itemRepository.save(Item.builder().brand(brandA).category(category2).price(5500).build());
        itemRepository.save(Item.builder().brand(brandA).category(category3).price(4200).build());
        itemRepository.save(Item.builder().brand(brandA).category(category4).price(9000).build());
        itemRepository.save(Item.builder().brand(brandA).category(category5).price(2000).build());
        itemRepository.save(Item.builder().brand(brandA).category(category6).price(1700).build());
        itemRepository.save(Item.builder().brand(brandA).category(category7).price(1800).build());
        itemRepository.save(Item.builder().brand(brandA).category(category8).price(2300).build());
        itemRepository.save(Item.builder().brand(brandB).category(category1).price(10500).build());
        itemRepository.save(Item.builder().brand(brandB).category(category2).price(5900).build());
        itemRepository.save(Item.builder().brand(brandB).category(category3).price(3800).build());
        itemRepository.save(Item.builder().brand(brandB).category(category4).price(9100).build());
        itemRepository.save(Item.builder().brand(brandB).category(category5).price(2100).build());
        itemRepository.save(Item.builder().brand(brandB).category(category6).price(2000).build());
        itemRepository.save(Item.builder().brand(brandB).category(category7).price(2000).build());
        itemRepository.save(Item.builder().brand(brandB).category(category8).price(2200).build());
        itemRepository.save(Item.builder().brand(brandC).category(category1).price(10000).build());
        itemRepository.save(Item.builder().brand(brandC).category(category2).price(6200).build());
        itemRepository.save(Item.builder().brand(brandC).category(category3).price(3300).build());
        itemRepository.save(Item.builder().brand(brandC).category(category4).price(9200).build());
        itemRepository.save(Item.builder().brand(brandC).category(category5).price(2200).build());
        itemRepository.save(Item.builder().brand(brandC).category(category6).price(1900).build());
        itemRepository.save(Item.builder().brand(brandC).category(category7).price(2200).build());
        itemRepository.save(Item.builder().brand(brandC).category(category8).price(2100).build());
        itemRepository.save(Item.builder().brand(brandD).category(category1).price(10100).build());
        itemRepository.save(Item.builder().brand(brandD).category(category2).price(5100).build());
        itemRepository.save(Item.builder().brand(brandD).category(category3).price(3000).build());
        itemRepository.save(Item.builder().brand(brandD).category(category4).price(9500).build());
        itemRepository.save(Item.builder().brand(brandD).category(category5).price(2500).build());
        itemRepository.save(Item.builder().brand(brandD).category(category6).price(1500).build());
        itemRepository.save(Item.builder().brand(brandD).category(category7).price(2400).build());
        itemRepository.save(Item.builder().brand(brandD).category(category8).price(2000).build());
        itemRepository.save(Item.builder().brand(brandE).category(category1).price(10700).build());
        itemRepository.save(Item.builder().brand(brandE).category(category2).price(5000).build());
        itemRepository.save(Item.builder().brand(brandE).category(category3).price(3800).build());
        itemRepository.save(Item.builder().brand(brandE).category(category4).price(9900).build());
        itemRepository.save(Item.builder().brand(brandE).category(category5).price(2300).build());
        itemRepository.save(Item.builder().brand(brandE).category(category6).price(1800).build());
        itemRepository.save(Item.builder().brand(brandE).category(category7).price(2100).build());
        itemRepository.save(Item.builder().brand(brandE).category(category8).price(2100).build());
        itemRepository.save(Item.builder().brand(brandF).category(category1).price(11200).build());
        itemRepository.save(Item.builder().brand(brandF).category(category2).price(7200).build());
        itemRepository.save(Item.builder().brand(brandF).category(category3).price(4000).build());
        itemRepository.save(Item.builder().brand(brandF).category(category4).price(9300).build());
        itemRepository.save(Item.builder().brand(brandF).category(category5).price(2100).build());
        itemRepository.save(Item.builder().brand(brandF).category(category6).price(1600).build());
        itemRepository.save(Item.builder().brand(brandF).category(category7).price(2300).build());
        itemRepository.save(Item.builder().brand(brandF).category(category8).price(1900).build());
        itemRepository.save(Item.builder().brand(brandG).category(category1).price(10500).build());
        itemRepository.save(Item.builder().brand(brandG).category(category2).price(5800).build());
        itemRepository.save(Item.builder().brand(brandG).category(category3).price(3900).build());
        itemRepository.save(Item.builder().brand(brandG).category(category4).price(9000).build());
        itemRepository.save(Item.builder().brand(brandG).category(category5).price(2200).build());
        itemRepository.save(Item.builder().brand(brandG).category(category6).price(1700).build());
        itemRepository.save(Item.builder().brand(brandG).category(category7).price(2100).build());
        itemRepository.save(Item.builder().brand(brandG).category(category8).price(2000).build());
        itemRepository.save(Item.builder().brand(brandH).category(category1).price(10800).build());
        itemRepository.save(Item.builder().brand(brandH).category(category2).price(6300).build());
        itemRepository.save(Item.builder().brand(brandH).category(category3).price(3100).build());
        itemRepository.save(Item.builder().brand(brandH).category(category4).price(9700).build());
        itemRepository.save(Item.builder().brand(brandH).category(category5).price(2100).build());
        itemRepository.save(Item.builder().brand(brandH).category(category6).price(1600).build());
        itemRepository.save(Item.builder().brand(brandH).category(category7).price(2000).build());
        itemRepository.save(Item.builder().brand(brandH).category(category8).price(2000).build());
        itemRepository.save(Item.builder().brand(brandI).category(category1).price(11400).build());
        itemRepository.save(Item.builder().brand(brandI).category(category2).price(6700).build());
        itemRepository.save(Item.builder().brand(brandI).category(category3).price(3200).build());
        itemRepository.save(Item.builder().brand(brandI).category(category4).price(9500).build());
        itemRepository.save(Item.builder().brand(brandI).category(category5).price(2400).build());
        itemRepository.save(Item.builder().brand(brandI).category(category6).price(1700).build());
        itemRepository.save(Item.builder().brand(brandI).category(category7).price(1700).build());
        itemRepository.save(Item.builder().brand(brandI).category(category8).price(2400).build());
    }
}
