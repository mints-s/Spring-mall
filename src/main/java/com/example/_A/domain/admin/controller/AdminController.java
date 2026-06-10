package com.example._A.domain.admin.controller;

import com.example._A.domain.article.command.DeleteArticleCommand;
import com.example._A.domain.article.command.handler.ArticleCommandHandler;
import com.example._A.domain.article.query.handler.ArticleQueryHandler;
import com.example._A.domain.comment.command.DeleteCommentCommand;
import com.example._A.domain.comment.command.handler.CommentCommandHandler;
import com.example._A.domain.comment.query.handler.CommentQueryHandler;
import com.example._A.domain.product.command.DeleteProductCommand;
import com.example._A.domain.product.command.DeleteProductImageCommand;
import com.example._A.domain.product.command.SetRepresentativeImageCommand;
import com.example._A.domain.product.command.UpdateProductCommand;
import com.example._A.domain.product.command.UploadProductImageCommand;
import com.example._A.domain.product.command.handler.ProductCommandHandler;
import com.example._A.domain.product.command.handler.ProductImageCommandHandler;
import com.example._A.domain.product.query.handler.ProductImageQueryHandler;
import com.example._A.domain.product.query.handler.ProductQueryHandler;
import com.example._A.domain.user.command.ActivateUserCommand;
import com.example._A.domain.user.command.ChangeUserRoleCommand;
import com.example._A.domain.user.command.WithdrawUserCommand;
import com.example._A.domain.user.command.handler.UserCommandHandler;
import com.example._A.domain.user.entity.UserRole;
import com.example._A.domain.user.query.handler.UserQueryHandler;
import com.example._A.global.common.PageInfo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Slf4j
@Controller
@RequestMapping("/admin")
@RequiredArgsConstructor
public class AdminController {

    private static final int PAGE_SIZE = 10;

    private final UserCommandHandler userCommandHandler;
    private final UserQueryHandler userQueryHandler;
    private final ProductCommandHandler productCommandHandler;
    private final ProductQueryHandler productQueryHandler;
    private final ProductImageCommandHandler productImageCommandHandler;
    private final ProductImageQueryHandler productImageQueryHandler;
    private final ArticleCommandHandler articleCommandHandler;
    private final ArticleQueryHandler articleQueryHandler;
    private final CommentCommandHandler commentCommandHandler;
    private final CommentQueryHandler commentQueryHandler;

    @GetMapping({"", "/"})
    public String dashboard() { return "redirect:/admin/users"; }

    // ── 유저 관리 ────────────────────────
    @GetMapping("/users")
    public String userList(@RequestParam(defaultValue = "0") int page, Model model) {
        var p = userQueryHandler.findAllPaged(PageRequest.of(page, PAGE_SIZE, Sort.by("id").descending()));
        model.addAttribute("users", p.getContent());
        model.addAttribute("pageInfo", PageInfo.of(p, ""));
        return "admin/users";
    }

    @PostMapping("/users/{id}/role")
    public String changeRole(@PathVariable Long id, @RequestParam UserRole role, RedirectAttributes rttr) {
        userCommandHandler.handle(new ChangeUserRoleCommand(id, role));
        rttr.addFlashAttribute("msg", "권한이 변경됐습니다.");
        return "redirect:/admin/users";
    }

    @PostMapping("/users/{id}/deactivate")
    public String deactivate(@PathVariable Long id, RedirectAttributes rttr) {
        userCommandHandler.handle(new WithdrawUserCommand(id));
        rttr.addFlashAttribute("msg", "계정이 비활성화됐습니다.");
        return "redirect:/admin/users";
    }

    @PostMapping("/users/{id}/activate")
    public String activate(@PathVariable Long id, RedirectAttributes rttr) {
        userCommandHandler.handle(new ActivateUserCommand(id));
        rttr.addFlashAttribute("msg", "계정이 활성화됐습니다.");
        return "redirect:/admin/users";
    }

    // ── 상품 관리 ────────────────────────
    @GetMapping("/products")
    public String productList(@RequestParam(defaultValue = "0") int page, Model model) {
        var p = productQueryHandler.findAll(PageRequest.of(page, PAGE_SIZE, Sort.by("id").descending()));
        model.addAttribute("products", p.getContent());
        model.addAttribute("pageInfo", PageInfo.of(p, ""));
        return "admin/products";
    }

    @GetMapping("/products/{id}/edit")
    public String editProductForm(@PathVariable Long id, Model model) {
        model.addAttribute("product", productQueryHandler.findById(id));
        model.addAttribute("categories", productQueryHandler.findCategories());
        model.addAttribute("images", productImageQueryHandler.findByProductId(id));
        model.addAttribute("productId", id);  // Mustache 루프 내 product id 접근용
        return "admin/product-edit";
    }

    @PostMapping("/products/{id}")
    public String updateProduct(@PathVariable Long id, UpdateProductCommand command,
                                @RequestParam(name = "images", required = false) java.util.List<MultipartFile> images,
                                RedirectAttributes rttr) {
        command.setId(id);
        productCommandHandler.handle(command);

        if (images != null) {
            for (MultipartFile file : images) {
                if (file != null && !file.isEmpty()) {
                    try {
                        productImageCommandHandler.handle(new UploadProductImageCommand(id, file, false));
                    } catch (Exception e) {
                        log.warn("이미지 업로드 실패: {}", e.getMessage());
                        rttr.addFlashAttribute("msg", "이미지 업로드 실패: " + e.getMessage());
                        return "redirect:/admin/products/" + id + "/edit";
                    }
                }
            }
        }
        rttr.addFlashAttribute("msg", "상품이 수정됐습니다.");
        return "redirect:/admin/products";
    }

    @PostMapping("/products/{productId}/images/{imageId}/delete")
    public String deleteProductImage(@PathVariable Long productId, @PathVariable Long imageId,
                                     RedirectAttributes rttr) {
        productImageCommandHandler.handle(new DeleteProductImageCommand(imageId));
        rttr.addFlashAttribute("msg", "이미지가 삭제됐습니다.");
        return "redirect:/admin/products/" + productId + "/edit";
    }

    @PostMapping("/products/{productId}/images/{imageId}/representative")
    public String setRepresentativeImage(@PathVariable Long productId, @PathVariable Long imageId,
                                          RedirectAttributes rttr) {
        productImageCommandHandler.handle(new SetRepresentativeImageCommand(productId, imageId));
        rttr.addFlashAttribute("msg", "대표 이미지가 변경됐습니다.");
        return "redirect:/admin/products/" + productId + "/edit";
    }

    @PostMapping("/products/{id}/delete")
    public String deleteProduct(@PathVariable Long id, RedirectAttributes rttr) {
        productCommandHandler.handle(new DeleteProductCommand(id));
        rttr.addFlashAttribute("msg", "상품이 삭제됐습니다.");
        return "redirect:/admin/products";
    }

    // ── 게시글 관리 ────────────────────────
    @GetMapping("/articles")
    public String articleList(@RequestParam(defaultValue = "0") int page, Model model) {
        var p = articleQueryHandler.findAllPaged(PageRequest.of(page, PAGE_SIZE, Sort.by("id").descending()));
        model.addAttribute("articles", p.getContent());
        model.addAttribute("pageInfo", PageInfo.of(p, ""));
        return "admin/articles";
    }

    @PostMapping("/articles/{id}/delete")
    public String deleteArticle(@PathVariable Long id, RedirectAttributes rttr) {
        articleCommandHandler.deleteWithComments(id);
        rttr.addFlashAttribute("msg", "게시글(댓글 포함)이 삭제됐습니다.");
        return "redirect:/admin/articles";
    }

    // ── 댓글 관리 ────────────────────────
    @GetMapping("/comments")
    public String commentList(@RequestParam(defaultValue = "0") int page, Model model) {
        var p = commentQueryHandler.findAllPaged(PageRequest.of(page, PAGE_SIZE, Sort.by("id").descending()));
        model.addAttribute("comments", p.getContent());
        model.addAttribute("pageInfo", PageInfo.of(p, ""));
        return "admin/comments";
    }

    @PostMapping("/comments/{id}/delete")
    public String deleteComment(@PathVariable Long id, RedirectAttributes rttr) {
        commentCommandHandler.handle(new DeleteCommentCommand(id));
        rttr.addFlashAttribute("msg", "댓글이 삭제됐습니다.");
        return "redirect:/admin/comments";
    }
}
