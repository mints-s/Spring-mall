package com.example._A.domain.code.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "common_code",
       uniqueConstraints = @UniqueConstraint(columnNames = {"code_group", "code_key"}))
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CommonCode {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "code_group", nullable = false, length = 50)
    private String codeGroup;   // "USER_ROLE", "PRODUCT_CATEGORY"

    @Column(name = "code_key", nullable = false, length = 50)
    private String codeKey;     // "ADMIN", "USER", "TV", "COMPUTER", "PHONE"

    @Column(name = "code_name", nullable = false, length = 100)
    private String codeName;    // "관리자", "일반 사용자", "TV", "컴퓨터", "휴대폰"

    @Column(name = "sort_order")
    private int sortOrder;

    @Builder.Default
    @Column(name = "is_active", nullable = false)
    private boolean active = true;
}
