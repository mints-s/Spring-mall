package com.example._A.domain.product.command.handler;

import com.example._A.domain.product.command.CreateProductCommand;
import com.example._A.domain.product.command.DeleteProductCommand;
import com.example._A.domain.product.command.UpdateProductCommand;
import com.example._A.domain.product.entity.Product;
import com.example._A.domain.product.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class ProductCommandHandler {

    private final ProductRepository productRepository;
    private final ProductImageCommandHandler productImageCommandHandler;

    public ProductCommandHandler(ProductRepository productRepository,
                                 @Lazy ProductImageCommandHandler productImageCommandHandler) {
        this.productRepository = productRepository;
        this.productImageCommandHandler = productImageCommandHandler;
    }

    public Product handle(CreateProductCommand command) {
        return productRepository.save(Product.builder()
                .name(command.getName())
                .price(command.getPrice())
                .category(command.getCategory())
                .description(command.getDescription())
                .build());
    }

    @Transactional
    public Product handle(UpdateProductCommand command) {
        Product product = productRepository.findById(command.getId())
                .orElseThrow(() -> new IllegalArgumentException("상품을 찾을 수 없습니다: " + command.getId()));
        product.update(command.getName(), command.getPrice(), command.getCategory(), command.getDescription());
        return productRepository.save(product);
    }

    @Transactional
    public void handle(DeleteProductCommand command) {
        // 연결된 이미지 파일 + 메타데이터 먼저 삭제
        productImageCommandHandler.deleteAllByProductId(command.id());
        productRepository.deleteById(command.id());
    }
}
