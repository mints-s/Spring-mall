package com.example._A.domain.user.command;

import com.example._A.domain.user.entity.UserRole;

public record ChangeUserRoleCommand(Long userId, UserRole role) {}
