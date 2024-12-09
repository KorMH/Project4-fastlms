package com.zerobase.fastlms.banner.dto;

import com.zerobase.fastlms.banner.entity.Banner;
import com.zerobase.fastlms.banner.model.BannerTarget;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class BannerDto {
    private Long id;
    private String name;
    private String file;
    private String url;
    private BannerTarget target;
    private int order;
    private Boolean openYN;
    private LocalDateTime addDt;
    private Boolean delFlag;

    private long totalCnt;
    private long seq;
    public static BannerDto fromEntity(Banner banner) {
        return BannerDto.builder()
                .id(banner.getBannerId())
                .name(banner.getName())
                .file(banner.getFile())
                .url(banner.getUrl())
                .target(banner.getTarget())
                .order(banner.getOrder())
                .openYN(banner.getOpenYN())
                .addDt(banner.getAddDt())
                .build();
    }
}
