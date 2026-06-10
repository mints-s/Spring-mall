package com.example._A.domain.article.repository;

import com.example._A.domain.article.entity.Article;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ArticleRepository extends JpaRepository<Article, Long> {
    List<Article> findByProduct_IdOrderByCreatedAtDesc(Long productId);
}
