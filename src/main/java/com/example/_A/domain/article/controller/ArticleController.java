package com.example._A.domain.article.controller;

import com.example._A.domain.article.command.CreateArticleCommand;
import com.example._A.domain.article.command.DeleteArticleCommand;
import com.example._A.domain.article.command.UpdateArticleCommand;
import com.example._A.domain.article.command.handler.ArticleCommandHandler;
import com.example._A.domain.article.entity.Article;
import com.example._A.domain.article.query.handler.ArticleQueryHandler;
import com.example._A.domain.comment.command.handler.CommentCommandHandler;
import com.example._A.domain.comment.query.handler.CommentQueryHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequiredArgsConstructor
public class ArticleController {

    private final ArticleCommandHandler articleCommandHandler;
    private final ArticleQueryHandler articleQueryHandler;
    private final CommentQueryHandler commentQueryHandler;

    @GetMapping("/articles/new")
    public String newArticleForm(@RequestParam(required = false) Long productId, Model model) {
        model.addAttribute("productId", productId);
        return "articles/new";
    }

    @PostMapping("/articles/create")
    public String createArticle(CreateArticleCommand command) {
        Article saved = articleCommandHandler.handle(command);
        if (command.getProductId() != null) {
            return "redirect:/products/" + command.getProductId() + "#reviews";
        }
        return "redirect:/articles/" + saved.getId();
    }

    @GetMapping("/articles/{id}")
    public String show(@PathVariable Long id, Model model) {
        Article article = articleQueryHandler.findById(id);
        model.addAttribute("article", article);
        model.addAttribute("comments", commentQueryHandler.findByArticleId(id));
        return "articles/show";
    }

    @GetMapping("/articles")
    public String index(Model model) {
        model.addAttribute("articleList", articleQueryHandler.findAll());
        return "articles/index";
    }

    @GetMapping("/articles/{id}/edit")
    public String edit(@PathVariable Long id, Model model) {
        model.addAttribute("article", articleQueryHandler.findById(id));
        return "articles/edit";
    }

    @PostMapping("/articles/update")
    public String update(UpdateArticleCommand command) {
        Article updated = articleCommandHandler.handle(command);
        return "redirect:/articles/" + (updated != null ? updated.getId() : command.getId());
    }

    @GetMapping("/articles/{id}/delete")
    public String delete(@PathVariable Long id, RedirectAttributes rttr) {
        Long productId = articleCommandHandler.deleteWithComments(id);
        rttr.addFlashAttribute("msg", "삭제됐습니다.");
        if (productId != null) return "redirect:/products/" + productId;
        return "redirect:/articles";
    }
}
