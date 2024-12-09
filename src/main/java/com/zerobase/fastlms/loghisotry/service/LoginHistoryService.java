package com.zerobase.fastlms.loghisotry.service;

import com.zerobase.fastlms.loghisotry.dto.LoginHistoryDto;
import com.zerobase.fastlms.loghisotry.entity.LoginHistory;

import java.util.List;

public interface LoginHistoryService {


    LoginHistoryDto saveLoginHistory(LoginHistory loginHistory);

    List<LoginHistoryDto> findUserLoginHistory(String userId);
}
