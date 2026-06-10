package com.example._A.domain.article.query.handler;

import com.example._A.domain.article.entity.Article;
import com.example._A.domain.article.repository.ArticleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class ArticleQueryHandler {

    private final ArticleRepository articleRepository;

    public List<Article> findAll() {
        return articleRepository.findAll();
    }

    public Article findById(Long id) {
        return articleRepository.findById(id).orElse(null);
    }

    public List<Article> findByProductId(Long productId) {
        return articleRepository.findByProduct_IdOrderByCreatedAtDesc(productId);
    }

    public Page<Article> findAllPaged(Pageable pageable) {
        return articleRepository.findAll(pageable);
    }
}
