package com.example._A.domain.article.service;

import com.example._A.domain.article.dto.ArticleForm;
import com.example._A.domain.article.entity.Article;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ArticleServiceTest {
    @Autowired
    ArticleService articleService;

    @Test
    void index() {
        Article a = new Article(1L, "첫 번째 게시글", "안녕하세요!");
        Article b = new Article(2L, "두 번째 게시글", "반갑습니다!");
        Article c = new Article(3L, "세 번째 게시글", "좋은 하루 되세요!");
        List<Article> expected = new ArrayList<>(Arrays.asList(a, b, c));
        List<Article> articles = articleService.index();
        assertEquals(expected.toString(), articles.toString());
    }

    @Test
    void show_succ_exist_id() {
        Long id = 1L;
        Article expected = new Article(id, "첫 번째 게시글", "안녕하세요!");
        Article article = articleService.show(id);
        assertEquals(expected.toString(), article.toString());
    }

    @Test
    void show_fail() {
        Long id = -1L;
        Article article = articleService.show(id);
        assertNull(article);
    }

    @Test
    @Transactional
    void create_succ() {
        String title = "ddddd";
        String content = "4444444444";
        ArticleForm dto = new ArticleForm(null, title, content);
        Article article = articleService.create(dto);
        assertEquals(title, article.getTitle());
        assertEquals(content, article.getContent());
    }

    @Test
    @Transactional
    void delete_succ() {
        Long id = 1L;
        Article article = articleService.delete(id);
        assertNotNull(article);
        assertEquals(id, article.getId());
    }
}
