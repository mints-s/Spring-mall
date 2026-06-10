package com.example._A.domain.product.repository;

import com.example._A.domain.product.entity.ProductImage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface ProductImageRepository extends JpaRepository<ProductImage, Long> {

    List<ProductImage> findByProductIdOrderByRepresentativeDesc(Long productId);

    Optional<ProductImage> findByProductIdAndRepresentativeTrue(Long productId);

    void deleteByProductId(Long productId);

    long countByProductId(Long productId);

    /** 목록 조회 시 대표 이미지 일괄 조회 (N+1 방지) */
    @Query("SELECT pi FROM ProductImage pi WHERE pi.product.id IN :productIds AND pi.representative = true")
    List<ProductImage> findRepresentativeByProductIds(@Param("productIds") List<Long> productIds);
}
