package com.example._A.domain.user.controller;

import com.example._A.domain.user.command.SignupUserCommand;
import com.example._A.domain.user.command.WithdrawUserCommand;
import com.example._A.domain.user.command.handler.UserCommandHandler;
import com.example._A.domain.user.dto.AddUserRequest;
import com.example._A.domain.user.entity.UserEntity;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.stream.Collectors;

@Controller
@RequiredArgsConstructor
public class UserApiController {

    private final UserCommandHandler userCommandHandler;

    @PostMapping("/user")
    public String signup(@Valid AddUserRequest request,
                         BindingResult bindingResult,
                         RedirectAttributes rttr) {
        if (bindingResult.hasErrors()) {
            String msg = bindingResult.getFieldErrors().stream()
                    .map(e -> e.getDefaultMessage())
                    .collect(Collectors.joining(", "));
            rttr.addFlashAttribute("signupError", msg);
            return "redirect:/signup";
        }
        try {
            userCommandHandler.handle(new SignupUserCommand(request.getEmail(), request.getPassword()));
            rttr.addFlashAttribute("signupSuccess", "회원가입이 완료됐습니다. 로그인해주세요.");
            return "redirect:/login";
        } catch (IllegalArgumentException e) {
            rttr.addFlashAttribute("signupError", e.getMessage());
            return "redirect:/signup";
        }
    }

    @PostMapping("/user/withdraw")
    public String withdraw(@AuthenticationPrincipal UserEntity currentUser,
                           HttpSession session) {
        userCommandHandler.handle(new WithdrawUserCommand(currentUser.getId()));
        session.invalidate();
        return "redirect:/login?withdrawn=true";
    }
}
