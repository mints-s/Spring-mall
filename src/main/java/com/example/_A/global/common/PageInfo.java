package com.example._A.global.common;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import org.springframework.data.domain.Page;

import java.util.ArrayList;
import java.util.List;

@Getter
@Builder
public class PageInfo {

    private int currentPage;      // 0-indexed
    private int currentDisplay;   // 1-indexed
    private int totalPages;
    private long totalElements;
    private boolean hasNext;
    private boolean hasPrevious;
    private int nextPage;
    private int previousPage;
    private List<PageNumber> pageNumbers;
    private String keyword;

    public static PageInfo of(Page<?> page, String keyword) {
        int current = page.getNumber();
        int total = page.getTotalPages();

        int start = Math.max(0, current - 4);
        int end = Math.min(total - 1, start + 9);
        if (end - start < 9) start = Math.max(0, end - 9);

        List<PageNumber> nums = new ArrayList<>();
        for (int i = start; i <= end; i++) {
            nums.add(new PageNumber(i, i + 1, i == current));
        }

        return PageInfo.builder()
                .currentPage(current)
                .currentDisplay(current + 1)
                .totalPages(total)
                .totalElements(page.getTotalElements())
                .hasNext(!page.isLast())
                .hasPrevious(!page.isFirst())
                .nextPage(current + 1)
                .previousPage(current - 1)
                .pageNumbers(nums)
                .keyword(keyword == null ? "" : keyword)
                .build();
    }

    @Getter
    @AllArgsConstructor
    public static class PageNumber {
        private int number;     // 0-indexed (URL param)
        private int display;    // 1-indexed (UI display)
        private boolean active;
    }
}
