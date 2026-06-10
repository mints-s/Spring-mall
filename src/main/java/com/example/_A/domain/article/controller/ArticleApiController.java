package com.example._A.domain.article.controller;

import com.example._A.domain.article.command.CreateArticleCommand;
import com.example._A.domain.article.command.DeleteArticleCommand;
import com.example._A.domain.article.command.UpdateArticleCommand;
import com.example._A.domain.article.command.handler.ArticleCommandHandler;
import com.example._A.domain.article.entity.Article;
import com.example._A.domain.article.query.handler.ArticleQueryHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class ArticleApiController {

    private final ArticleCommandHandler articleCommandHandler;
    private final ArticleQueryHandler articleQueryHandler;

    @GetMapping("/api/articles")
    public List<Article> index() {
        return articleQueryHandler.findAll();
    }

    @GetMapping("/api/articles/{id}")
    public Article show(@PathVariable Long id) {
        return articleQueryHandler.findById(id);
    }

    @PostMapping("/api/articles")
    public ResponseEntity<Article> create(@RequestBody CreateArticleCommand command) {
        return ResponseEntity.ok(articleCommandHandler.handle(command));
    }

    @PatchMapping("/api/articles/{id}")
    public ResponseEntity<Article> update(@PathVariable Long id, @RequestBody UpdateArticleCommand command) {
        command.setId(id);
        Article updated = articleCommandHandler.handle(command);
        return updated != null ? ResponseEntity.ok(updated)
                : ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }

    @DeleteMapping("/api/articles/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        Article deleted = articleCommandHandler.handle(new DeleteArticleCommand(id));
        return deleted != null ? ResponseEntity.ok().build()
                : ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }

    @PostMapping("/api/transaction")
    public ResponseEntity<List<Article>> transactionTest(@RequestBody List<CreateArticleCommand> commands) {
        List<Article> result = articleCommandHandler.handleBulk(commands);
        return result != null ? ResponseEntity.ok(result)
                : ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }
}
