package com.example._A.domain.article.dto;

import com.example._A.domain.article.entity.Article;
import lombok.AllArgsConstructor;
import lombok.ToString;

@AllArgsConstructor
@ToString
@lombok.Getter
public class ArticleForm {
    private Long id;
    private String title;
    private String content;

    public Article toEntity() {
        return Article.builder().id(id).title(title).content(content).build();
    }
}
