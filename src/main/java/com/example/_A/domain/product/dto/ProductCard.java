package com.example._A.domain.product.dto;

import com.example._A.domain.product.entity.Product;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ProductCard {

    private Long id;
    private String name;
    private Long price;
    private String category;
    private String description;
    private String createdAtFormatted;
    private String imageUrl;           // 대표 이미지 URL, 없으면 null

    public boolean isHasImage() {
        return imageUrl != null && !imageUrl.isBlank();
    }

    public static ProductCard of(Product product, String imageUrl) {
        return new ProductCard(
                product.getId(),
                product.getName(),
                product.getPrice(),
                product.getCategory(),
                product.getDescription(),
                product.getCreatedAtFormatted(),
                imageUrl
        );
    }
}
