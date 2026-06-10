package com.example._A.domain.product.entity;

import com.example._A.global.entity.BaseTimeEntity;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "product_image")
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class ProductImage extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id", nullable = false)
    private Product product;

    @Column(name = "original_file_name", nullable = false)
    private String originalFileName;  // 원본 파일명

    @Column(name = "stored_file_name", nullable = false, unique = true)
    private String storedFileName;    // UUID 기반 저장 파일명

    @Column(name = "file_path", nullable = false)
    private String filePath;          // 디스크 저장 경로

    @Column(name = "file_size")
    private Long fileSize;            // 바이트 단위

    @Column(name = "content_type", length = 50)
    private String contentType;       // MIME 타입 (image/jpeg 등)

    @Builder.Default
    @Column(name = "is_representative", nullable = false)
    private boolean representative = false;  // 대표 이미지 여부

    /** 브라우저에서 접근하는 URL */
    public String getImageUrl() {
        return "/uploads/products/" + storedFileName;
    }

    public void promoteToRepresentative() {
        this.representative = true;
    }

    public void demoteFromRepresentative() {
        this.representative = false;
    }
}
