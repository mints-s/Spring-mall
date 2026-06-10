package com.example._A.domain.product.command;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.web.multipart.MultipartFile;

@Getter
@AllArgsConstructor
public class UploadProductImageCommand {
    private final Long productId;
    private final MultipartFile file;
    private final boolean representative;
}
