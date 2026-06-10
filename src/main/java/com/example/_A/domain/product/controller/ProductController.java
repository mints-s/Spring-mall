package com.example._A.domain.product.controller;

import com.example._A.domain.article.query.handler.ArticleQueryHandler;
import com.example._A.domain.product.command.CreateProductCommand;
import com.example._A.domain.product.command.UploadProductImageCommand;
import com.example._A.domain.product.command.handler.ProductCommandHandler;
import com.example._A.domain.product.command.handler.ProductImageCommandHandler;
import com.example._A.domain.product.entity.Product;
import com.example._A.domain.product.query.handler.ProductImageQueryHandler;
import com.example._A.domain.product.query.handler.ProductQueryHandler;
import com.example._A.global.common.PageInfo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Slf4j
@Controller
@RequiredArgsConstructor
public class ProductController {

    private static final int PAGE_SIZE = 9;

    private final ProductCommandHandler productCommandHandler;
    private final ProductQueryHandler productQueryHandler;
    private final ProductImageCommandHandler productImageCommandHandler;
    private final ProductImageQueryHandler productImageQueryHandler;
    private final ArticleQueryHandler articleQueryHandler;

    @GetMapping("/products")
    public String productList(@RequestParam(defaultValue = "0") int page, Model model) {
        Page<Product> productPage = productQueryHandler.findAll(
                PageRequest.of(page, PAGE_SIZE, Sort.by("id").descending()));
        model.addAttribute("products", productQueryHandler.toCards(productPage.getContent()));
        model.addAttribute("pageInfo", PageInfo.of(productPage, ""));
        model.addAttribute("pageTitle", "전체 상품");
        model.addAttribute("keyword", "");
        return "product/list";
    }

    @GetMapping("/products/search")
    public String search(@RequestParam(defaultValue = "") String keyword,
                         @RequestParam(defaultValue = "0") int page, Model model) {
        Page<Product> productPage = keyword.isBlank()
                ? productQueryHandler.findAll(PageRequest.of(page, PAGE_SIZE, Sort.by("id").descending()))
                : productQueryHandler.searchByName(keyword, PageRequest.of(page, PAGE_SIZE, Sort.by("id").descending()));
        model.addAttribute("products", productQueryHandler.toCards(productPage.getContent()));
        model.addAttribute("pageInfo", PageInfo.of(productPage, keyword));
        model.addAttribute("keyword", keyword);
        model.addAttribute("pageTitle", keyword.isBlank() ? "전체 상품" : "\"" + keyword + "\" 검색 결과");
        return "product/list";
    }

    @GetMapping("/products/{id}")
    public String detail(@PathVariable Long id, Model model) {
        model.addAttribute("product", productQueryHandler.findById(id));
        model.addAttribute("images", productImageQueryHandler.findByProductId(id));
        model.addAttribute("articles", articleQueryHandler.findByProductId(id));
        model.addAttribute("productId", id);
        return "product/show";
    }

    @GetMapping("/product/category/{cat}")
    public String byCategory(@PathVariable String cat,
                             @RequestParam(defaultValue = "0") int page, Model model) {
        Page<Product> productPage = productQueryHandler.findByCategory(cat,
                PageRequest.of(page, PAGE_SIZE, Sort.by("id").descending()));
        model.addAttribute("products", productQueryHandler.toCards(productPage.getContent()));
        model.addAttribute("pageInfo", PageInfo.of(productPage, ""));
        model.addAttribute("pageTitle", cat + " 카테고리");
        model.addAttribute("keyword", "");
        return "product/list";
    }

    @GetMapping("/product/new")
    public String newProductForm(Model model) {
        model.addAttribute("categories", productQueryHandler.findCategories());
        return "product/new";
    }

    @PostMapping("/product/create")
    public String createProduct(CreateProductCommand command,
                                @RequestParam(name = "images", required = false) List<MultipartFile> images,
                                RedirectAttributes rttr) {
        Product saved = productCommandHandler.handle(command);

        if (images != null) {
            boolean first = true;
            for (MultipartFile file : images) {
                if (file != null && !file.isEmpty()) {
                    try {
                        productImageCommandHandler.handle(
                                new UploadProductImageCommand(saved.getId(), file, first));
                        first = false;
                    } catch (Exception e) {
                        log.warn("이미지 업로드 실패: {}", e.getMessage());
                        rttr.addFlashAttribute("imageError", "일부 이미지 업로드에 실패했습니다: " + e.getMessage());
                    }
                }
            }
        }
        return "redirect:/products/" + saved.getId();
    }
}
