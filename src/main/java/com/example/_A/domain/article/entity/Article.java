package com.example._A.domain.article.entity;

import com.example._A.domain.product.entity.Product;
import com.example._A.global.entity.BaseTimeEntity;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString(exclude = "product")
public class Article extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id")
    private Product product;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false, length = 2000)
    private String content;

    public void patch(String title, String content) {
        if (title != null) this.title = title;
        if (content != null) this.content = content;
    }

    /** 상품명 (템플릿 편의용) */
    public String getProductName() {
        return product != null ? product.getName() : null;
    }

    public Long getProductId() {
        return product != null ? product.getId() : null;
    }
}
