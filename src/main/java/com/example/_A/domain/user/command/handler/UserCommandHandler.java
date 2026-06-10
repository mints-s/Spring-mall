package com.example._A.domain.user.command.handler;

import com.example._A.domain.user.command.*;
import com.example._A.domain.user.entity.UserEntity;
import com.example._A.domain.user.entity.UserRole;
import com.example._A.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Component
@RequiredArgsConstructor
public class UserCommandHandler {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Transactional
    public void handle(SignupUserCommand command) {
        if (userRepository.existsByEmail(command.getEmail())) {
            throw new IllegalArgumentException("이미 사용 중인 이메일입니다: " + command.getEmail());
        }
        userRepository.save(UserEntity.builder()
                .email(command.getEmail())
                .password(bCryptPasswordEncoder.encode(command.getPassword()))
                .role(UserRole.USER)
                .build());
        log.info("신규 회원 가입: {}", command.getEmail());
    }

    public void handleCreateAdmin(String email, String rawPassword) {
        if (!userRepository.existsByEmail(email)) {
            userRepository.save(UserEntity.builder()
                    .email(email)
                    .password(bCryptPasswordEncoder.encode(rawPassword))
                    .role(UserRole.ADMIN)
                    .build());
            log.info("기본 관리자 계정 생성: {}", email);
        }
    }

    @Transactional
    public void handle(WithdrawUserCommand command) {
        UserEntity user = findById(command.userId());
        user.deactivate();
        userRepository.save(user);
        log.info("회원 탈퇴 userId={}", command.userId());
    }

    @Transactional
    public void handle(ActivateUserCommand command) {
        UserEntity user = findById(command.userId());
        user.activate();
        userRepository.save(user);
        log.info("회원 활성화 userId={}", command.userId());
    }

    @Transactional
    public void handle(ChangeUserRoleCommand command) {
        UserEntity user = findById(command.userId());
        user.changeRole(command.role());
        userRepository.save(user);
        log.info("권한 변경 userId={} role={}", command.userId(), command.role());
    }

    private UserEntity findById(Long userId) {
        return userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("사용자를 찾을 수 없습니다: " + userId));
    }
}
