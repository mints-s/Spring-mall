package com.example._A.domain.product.query.handler;

import com.example._A.domain.code.entity.CommonCode;
import com.example._A.domain.code.repository.CommonCodeRepository;
import com.example._A.domain.product.dto.ProductCard;
import com.example._A.domain.product.entity.Product;
import com.example._A.domain.product.entity.ProductImage;
import com.example._A.domain.product.repository.ProductImageRepository;
import com.example._A.domain.product.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class ProductQueryHandler {

    private final ProductRepository productRepository;
    private final CommonCodeRepository commonCodeRepository;
    private final ProductImageRepository productImageRepository;

    public Product findById(Long id) {
        return productRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("상품을 찾을 수 없습니다: " + id));
    }

    public Page<Product> findAll(Pageable pageable) {
        return productRepository.findAll(pageable);
    }

    public Page<Product> findByCategory(String category, Pageable pageable) {
        return productRepository.findByCategory(category, pageable);
    }

    public Page<Product> searchByName(String keyword, Pageable pageable) {
        return productRepository.findByNameContainingIgnoreCase(keyword, pageable);
    }

    public List<CommonCode> findCategories() {
        return commonCodeRepository.findActiveByGroup("PRODUCT_CATEGORY");
    }

    /**
     * 상품 목록을 ProductCard(이미지 URL 포함)로 변환.
     * 대표 이미지를 한 번의 배치 쿼리로 조회해 N+1 방지.
     */
    public List<ProductCard> toCards(List<Product> products) {
        if (products.isEmpty()) return List.of();

        List<Long> ids = products.stream().map(Product::getId).collect(Collectors.toList());

        // 대표 이미지 일괄 조회 → productId: imageUrl 맵 생성
        Map<Long, String> imageMap = productImageRepository.findRepresentativeByProductIds(ids)
                .stream()
                .collect(Collectors.toMap(
                        pi -> pi.getProduct().getId(),
                        ProductImage::getImageUrl
                ));

        return products.stream()
                .map(p -> ProductCard.of(p, imageMap.get(p.getId())))
                .collect(Collectors.toList());
    }
}
