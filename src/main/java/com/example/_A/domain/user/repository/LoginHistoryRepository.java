package com.example._A.domain.user.repository;

import com.example._A.domain.user.entity.LoginHistory;
import com.example._A.domain.user.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LoginHistoryRepository extends JpaRepository<LoginHistory, Long> {
    List<LoginHistory> findTop5ByUserOrderByLoginAtDesc(UserEntity user);
}
