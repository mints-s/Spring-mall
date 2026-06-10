package com.example._A.domain.comment.repository;

import com.example._A.domain.article.entity.Article;
import com.example._A.domain.comment.entity.Comment;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class CommentRepositoryTest {
    @Autowired
    CommentRepository commentRepository;

    @Test
    @DisplayName("read all comments")
    void findByArticleId() {
        Long articleId = 4L;
        List<Comment> comments = commentRepository.findByArticleId(articleId);
        Article article = new Article(4L, "movie", "add comment.....");
        Comment a = new Comment(1L, article, "kim", "movie1");
        Comment b = new Comment(2L, article, "lee", "movie2");
        Comment c = new Comment(3L, article, "park", "movie3");
        assertEquals(List.of(a, b, c).toString(), comments.toString(), "success.....");
    }
}
