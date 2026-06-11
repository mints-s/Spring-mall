package com.example._A.global.init;

import com.example._A.domain.code.entity.CommonCode;
import com.example._A.domain.code.repository.CommonCodeRepository;
import com.example._A.domain.user.command.handler.UserCommandHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class DataInitializer {

    private static final String PRODUCT_CATEGORY = "PRODUCT_CATEGORY";

    private final UserCommandHandler userCommandHandler;
    private final CommonCodeRepository commonCodeRepository;

    @EventListener(ApplicationReadyEvent.class)
    public void initBaseData() {
        userCommandHandler.handleCreateAdmin("admin@shop.com", "admin1234");
        initProductCategories();
    }

    private void initProductCategories() {
        List<CommonCode> defaultCategories = List.of(
                category("TV", "TV", 1),
                category("COMPUTER", "컴퓨터", 2),
                category("PHONE", "휴대폰", 3)
        );

        defaultCategories.stream()
                .filter(code -> !commonCodeRepository.existsByCodeGroupAndCodeKey(
                        code.getCodeGroup(), code.getCodeKey()))
                .forEach(commonCodeRepository::save);
    }

    private CommonCode category(String key, String name, int sortOrder) {
        return CommonCode.builder()
                .codeGroup(PRODUCT_CATEGORY)
                .codeKey(key)
                .codeName(name)
                .sortOrder(sortOrder)
                .active(true)
                .build();
    }
}
