package com.example._A.domain.code.repository;

import com.example._A.domain.code.entity.CommonCode;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommonCodeRepository extends JpaRepository<CommonCode, Long> {
    List<CommonCode> findByCodeGroupAndActiveOrderBySortOrder(String codeGroup, boolean active);

    default List<CommonCode> findActiveByGroup(String codeGroup) {
        return findByCodeGroupAndActiveOrderBySortOrder(codeGroup, true);
    }
}
