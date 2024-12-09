package com.zerobase.fastlms.loghisotry.service.Impl;

import com.zerobase.fastlms.loghisotry.dto.LoginHistoryDto;
import com.zerobase.fastlms.loghisotry.entity.LoginHistory;
import com.zerobase.fastlms.loghisotry.repository.LoginHistoryRepository;
import com.zerobase.fastlms.loghisotry.service.LoginHistoryService;
import com.zerobase.fastlms.member.entity.Member;
import com.zerobase.fastlms.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class LoginHistoryServiceImpl implements LoginHistoryService {


    private final LoginHistoryRepository loginHistoryRepository;
    private final MemberRepository memberRepository;


    @Override
    @Transactional
    public LoginHistoryDto saveLoginHistory(LoginHistory loginHistory) {

        LoginHistory savedHistory = loginHistoryRepository.save(loginHistory);
        loginHistory.getMember().getHistories().add(loginHistory);

        return LoginHistoryDto.fromEntity(savedHistory);
    }

    @Override
    public List<LoginHistoryDto> findUserLoginHistory(String userId) {
        Member findMember = memberRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("userId를 찾을 수 없습니다." + userId));

        return findMember.getHistories()
                .stream()
                .map(LoginHistoryDto::fromEntity)
                .collect(Collectors.toList());
    }
}
