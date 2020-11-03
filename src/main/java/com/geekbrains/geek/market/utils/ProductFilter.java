package com.geekbrains.geek.market.utils;

import com.geekbrains.geek.market.entities.Product;
import com.geekbrains.geek.market.repositories.specifications.ProductSpecifications;
import lombok.Getter;
import org.springframework.data.jpa.domain.Specification;

import java.util.Map;

@Getter
public class ProductFilter {
    private Specification<Product> spec;

    public ProductFilter(Map<String, String> params) {
        spec = Specification.where(null);

        String filterTitle = params.get("title");
        if (filterTitle != null && !filterTitle.isEmpty()) {
            spec = spec.and(ProductSpecifications.titleLike(filterTitle));
        }

        if (params.containsKey("min_price") && !params.get("min_price").isEmpty()) {
            Integer minPrice = Integer.parseInt(params.get("min_price"));
            spec = spec.and(ProductSpecifications.priceGreaterOrEqualsThan(minPrice));
        }

        if (params.containsKey("max_price") && !params.get("max_price").isEmpty()) {
            Integer maxPrice = Integer.parseInt(params.get("max_price"));
            spec = spec.and(ProductSpecifications.priceLesserOrEqualsThan(maxPrice));
        }

        if (params.containsKey("category_id") && !params.get("category_id").isEmpty()) {
            Long categoryId = Long.parseLong(params.get("category_id"));
            spec = spec.and(ProductSpecifications.categoryIdIs(categoryId));
        }
    }
}
