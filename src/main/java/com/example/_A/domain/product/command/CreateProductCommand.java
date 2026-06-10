package com.example._A.domain.product.command;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateProductCommand {
    private String name;
    private Long price;
    private String category;
    private String description;
}
