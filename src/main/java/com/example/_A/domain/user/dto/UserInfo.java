package com.example._A.domain.user.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Getter
@AllArgsConstructor
public class UserInfo {
    private Long id;
    private String email;
    private LocalDateTime createdAt;

    public String getCreatedAtFormatted() {
        if (createdAt == null) return "-";
        return createdAt.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
    }
}
