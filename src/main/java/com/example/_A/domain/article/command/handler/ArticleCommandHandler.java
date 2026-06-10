package com.example._A.domain.article.command.handler;

import com.example._A.domain.article.command.CreateArticleCommand;
import com.example._A.domain.article.command.DeleteArticleCommand;
import com.example._A.domain.article.command.UpdateArticleCommand;
import com.example._A.domain.article.entity.Article;
import com.example._A.domain.article.repository.ArticleRepository;
import com.example._A.domain.comment.repository.CommentRepository;
import com.example._A.domain.product.entity.Product;
import com.example._A.domain.product.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class ArticleCommandHandler {

    private final ArticleRepository articleRepository;
    private final CommentRepository commentRepository;
    private final ProductRepository productRepository;

    public Article handle(CreateArticleCommand command) {
        Product product = null;
        if (command.getProductId() != null) {
            product = productRepository.findById(command.getProductId()).orElse(null);
        }
        return articleRepository.save(Article.builder()
                .product(product)
                .title(command.getTitle())
                .content(command.getContent())
                .build());
    }

    public Article handle(UpdateArticleCommand command) {
        Article target = articleRepository.findById(command.getId()).orElse(null);
        if (target == null) return null;
        target.patch(command.getTitle(), command.getContent());
        return articleRepository.save(target);
    }

    public Article handle(DeleteArticleCommand command) {
        Article target = articleRepository.findById(command.id()).orElse(null);
        if (target == null) return null;
        articleRepository.delete(target);
        return target;
    }

    /** 게시글 + 댓글 삭제 후 연결된 상품 ID 반환 */
    @Transactional
    public Long deleteWithComments(Long id) {
        Article article = articleRepository.findById(id).orElse(null);
        Long productId = (article != null) ? article.getProductId() : null;
        commentRepository.deleteByArticleId(id);
        articleRepository.deleteById(id);
        return productId;
    }

    @Transactional
    public List<Article> handleBulk(List<CreateArticleCommand> commands) {
        List<Article> articles = commands.stream()
                .map(cmd -> Article.builder().title(cmd.getTitle()).content(cmd.getContent()).build())
                .collect(Collectors.toList());
        articles.forEach(articleRepository::save);
        articleRepository.findById(-1L)
                .orElseThrow(() -> new IllegalArgumentException("fault...."));
        return articles;
    }
}
