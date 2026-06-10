package com.example._A.global.security;

import com.example._A.domain.user.entity.LoginHistory;
import com.example._A.domain.user.entity.UserEntity;
import com.example._A.domain.user.entity.UserRole;
import com.example._A.domain.user.repository.LoginHistoryRepository;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.time.LocalDateTime;

@Slf4j
@Component
@RequiredArgsConstructor
public class LoginSuccessHandler implements AuthenticationSuccessHandler {

    private final LoginHistoryRepository loginHistoryRepository;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request,
                                        HttpServletResponse response,
                                        Authentication authentication) throws IOException {
        UserEntity user = (UserEntity) authentication.getPrincipal();

        loginHistoryRepository.save(LoginHistory.builder()
                .user(user)
                .loginAt(LocalDateTime.now())
                .ipAddress(getClientIp(request))
                .build());

        log.info("로그인 성공 - email={}, role={}, ip={}", user.getEmail(), user.getRole(), getClientIp(request));

        if (user.getRole() == UserRole.ADMIN) {
            response.sendRedirect("/admin/users");
        } else {
            response.sendRedirect("/products");
        }
    }

    private String getClientIp(HttpServletRequest request) {
        String ip = request.getHeader("X-Forwarded-For");
        if (ip == null || ip.isBlank()) ip = request.getRemoteAddr();
        return ip;
    }
}
