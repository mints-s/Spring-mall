package com.example._A.domain.product.command.handler;

import com.example._A.domain.product.command.DeleteProductImageCommand;
import com.example._A.domain.product.command.SetRepresentativeImageCommand;
import com.example._A.domain.product.command.UploadProductImageCommand;
import com.example._A.domain.product.entity.Product;
import com.example._A.domain.product.entity.ProductImage;
import com.example._A.domain.product.repository.ProductImageRepository;
import com.example._A.domain.product.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;
import java.util.Set;
import java.util.UUID;

@Slf4j
@Component
@RequiredArgsConstructor
public class ProductImageCommandHandler {

    private static final Set<String> ALLOWED_TYPES =
            Set.of("image/jpeg", "image/png", "image/gif", "image/webp");

    private final ProductImageRepository productImageRepository;
    private final ProductRepository productRepository;

    @Value("${app.upload.product-dir}")
    private String uploadDir;

    @Transactional
    public ProductImage handle(UploadProductImageCommand command) throws IOException {
        MultipartFile file = command.getFile();
        validateFile(file);

        // 저장 파일명 생성 (UUID + 확장자)
        String extension = extractExtension(file.getOriginalFilename());
        String storedFileName = UUID.randomUUID() + "." + extension;

        // 절대 경로 사용 (Tomcat 상대경로 문제 방지)
        Path dir = Paths.get(uploadDir).toAbsolutePath().normalize();
        Files.createDirectories(dir);
        Path savedPath = dir.resolve(storedFileName);

        // transferTo() 대신 InputStream 복사 방식 사용 (항상 안정적으로 동작)
        try (InputStream inputStream = file.getInputStream()) {
            Files.copy(inputStream, savedPath, StandardCopyOption.REPLACE_EXISTING);
        }

        log.info("이미지 저장 완료: {} → {}", file.getOriginalFilename(), savedPath);

        // 기존 이미지가 없으면 자동으로 대표 이미지 지정
        boolean isRepresentative = command.isRepresentative()
                || productImageRepository.countByProductId(command.getProductId()) == 0;

        Product product = productRepository.findById(command.getProductId())
                .orElseThrow(() -> new IllegalArgumentException("상품을 찾을 수 없습니다: " + command.getProductId()));

        ProductImage image = ProductImage.builder()
                .product(product)
                .originalFileName(file.getOriginalFilename())
                .storedFileName(storedFileName)
                .filePath(savedPath.toAbsolutePath().toString())
                .fileSize(file.getSize())
                .contentType(file.getContentType())
                .representative(isRepresentative)
                .build();

        return productImageRepository.save(image);
    }

    @Transactional
    public void handle(DeleteProductImageCommand command) {
        ProductImage image = productImageRepository.findById(command.imageId())
                .orElseThrow(() -> new IllegalArgumentException("이미지를 찾을 수 없습니다: " + command.imageId()));

        boolean wasRepresentative = image.isRepresentative();
        Long productId = image.getProduct().getId();

        // 디스크에서 파일 삭제
        deleteFile(image.getFilePath());
        productImageRepository.delete(image);

        // 대표 이미지 삭제 시 다음 이미지를 대표로 승격
        if (wasRepresentative) {
            productImageRepository.findByProductIdOrderByRepresentativeDesc(productId)
                    .stream().findFirst()
                    .ifPresent(next -> {
                        next.promoteToRepresentative();
                        productImageRepository.save(next);
                    });
        }
    }

    @Transactional
    public void handle(SetRepresentativeImageCommand command) {
        // 기존 대표 이미지 해제
        productImageRepository.findByProductIdAndRepresentativeTrue(command.productId())
                .ifPresent(existing -> {
                    existing.demoteFromRepresentative();
                    productImageRepository.save(existing);
                });

        // 새 대표 이미지 지정
        ProductImage image = productImageRepository.findById(command.imageId())
                .orElseThrow(() -> new IllegalArgumentException("이미지를 찾을 수 없습니다: " + command.imageId()));
        image.promoteToRepresentative();
        productImageRepository.save(image);
    }

    /** 상품 삭제 시 관련 이미지 일괄 삭제 */
    @Transactional
    public void deleteAllByProductId(Long productId) {
        List<ProductImage> images = productImageRepository.findByProductIdOrderByRepresentativeDesc(productId);
        images.forEach(img -> deleteFile(img.getFilePath()));
        productImageRepository.deleteByProductId(productId);
        log.info("상품 {}의 이미지 {}개 삭제 완료", productId, images.size());
    }

    private void validateFile(MultipartFile file) {
        if (file == null || file.isEmpty()) {
            throw new IllegalArgumentException("파일이 비어있습니다.");
        }
        if (!ALLOWED_TYPES.contains(file.getContentType())) {
            throw new IllegalArgumentException("허용되지 않는 파일 형식입니다: " + file.getContentType()
                    + " (허용: JPG, PNG, GIF, WebP)");
        }
        if (file.getSize() > 10 * 1024 * 1024) {
            throw new IllegalArgumentException("파일 크기는 10MB를 초과할 수 없습니다.");
        }
    }

    private String extractExtension(String filename) {
        if (filename == null || !filename.contains(".")) return "jpg";
        return filename.substring(filename.lastIndexOf('.') + 1).toLowerCase();
    }

    private void deleteFile(String filePath) {
        try {
            boolean deleted = Files.deleteIfExists(Paths.get(filePath));
            if (deleted) log.info("파일 삭제: {}", filePath);
        } catch (IOException e) {
            log.warn("파일 삭제 실패: {} - {}", filePath, e.getMessage());
        }
    }
}
