package com.example._A.domain.product.query.handler;

import com.example._A.domain.product.entity.ProductImage;
import com.example._A.domain.product.repository.ProductImageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class ProductImageQueryHandler {

    private final ProductImageRepository productImageRepository;

    public List<ProductImage> findByProductId(Long productId) {
        return productImageRepository.findByProductIdOrderByRepresentativeDesc(productId);
    }

    public Optional<ProductImage> findRepresentative(Long productId) {
        return productImageRepository.findByProductIdAndRepresentativeTrue(productId);
    }
}
