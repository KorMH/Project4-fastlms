package com.zerobase.fastlms.member.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.zerobase.fastlms.loghisotry.entity.LoginHistory;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@Entity
public class Member implements MemberCode {
    
    @Id
    private String userId;
    private String userName;
    private String phone;
    private String password;
    private LocalDateTime regDt;
    private LocalDateTime udtDt;//회원정보 수정일
    
    private boolean emailAuthYn;
    private LocalDateTime emailAuthDt;
    private String emailAuthKey;
    
    private String resetPasswordKey;
    private LocalDateTime resetPasswordLimitDt;
    
    private boolean adminYn;
    
    private String userStatus;//이용가능한상태, 정지상태
    
    
    private String zipcode;
    private String addr;
    private String addrDetail;

    @JsonFormat(pattern = "yyyy.MM.dd HH:mm")
    private LocalDateTime lastLoginDt;

    @OneToMany(mappedBy = "member", fetch = FetchType.EAGER)
    private List<LoginHistory> histories = new LinkedList<>();
}
