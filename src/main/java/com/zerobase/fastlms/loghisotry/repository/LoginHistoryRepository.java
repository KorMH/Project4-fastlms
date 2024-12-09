package com.zerobase.fastlms.loghisotry.repository;

import com.zerobase.fastlms.loghisotry.entity.LoginHistory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LoginHistoryRepository extends JpaRepository<LoginHistory, Long> {
}
