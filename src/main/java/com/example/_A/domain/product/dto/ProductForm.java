package com.example._A.domain.product.dto;

import com.example._A.domain.product.entity.Product;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@AllArgsConstructor
@ToString
public class ProductForm {
    private Long id;
    private String name;
    private Long price;
    private String category;
    private String description;

    public Product toEntity() {
        return new Product(id, name, price, category, description);
    }
}
