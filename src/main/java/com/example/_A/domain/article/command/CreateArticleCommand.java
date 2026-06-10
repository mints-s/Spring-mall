package com.example._A.domain.article.command;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateArticleCommand {
    private Long productId;   // 연결된 상품 ID (리뷰/게시글)
    private String title;
    private String content;
}
