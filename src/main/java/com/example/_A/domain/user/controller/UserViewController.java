package com.example._A.domain.user.controller;

import com.example._A.domain.user.entity.UserEntity;
import com.example._A.domain.user.entity.UserRole;
import com.example._A.domain.user.repository.LoginHistoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequiredArgsConstructor
public class UserViewController {

    private final LoginHistoryRepository loginHistoryRepository;

    @GetMapping("/login")
    public String loginPage(@RequestParam(defaultValue = "false") boolean error,
                            @RequestParam(defaultValue = "false") boolean withdrawn,
                            Model model) {
        if (error) model.addAttribute("loginError", "이메일 또는 비밀번호가 올바르지 않습니다.");
        if (withdrawn) model.addAttribute("withdrawnMsg", "탈퇴가 완료됐습니다. 이용해주셔서 감사합니다.");
        return "login";
    }

    @GetMapping("/signup")
    public String signupPage() {
        return "signup";
    }

    @GetMapping("/user/profile")
    public String profilePage(@AuthenticationPrincipal UserEntity user, Model model) {
        if (user == null) return "redirect:/login";
        if (user.getRole() == UserRole.ADMIN) return "redirect:/admin/users";
        model.addAttribute("loginHistory",
                loginHistoryRepository.findTop5ByUserOrderByLoginAtDesc(user));
        return "user/profile";
    }
}
