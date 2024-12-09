package com.zerobase.fastlms.banner.entity;


import com.zerobase.fastlms.banner.model.BannerInput;
import com.zerobase.fastlms.banner.model.BannerTarget;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Builder
@Entity
public class Banner {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "banner_id")
    private Long bannerId;

    @Column(name = "banner_name")
    private String name;

    @Column(name = "banner_file")
    private String file;

    @Column(name = "banner_file_url")
    private String url;

    @Column(name = "banner_open_target")
    @Enumerated(value = EnumType.STRING)
    private BannerTarget target;

    @Column(name = "banner_order")
    private int order;

    @Column(name = "banner_open_yn")
    private Boolean openYN;

    @Column(name = "banner_add_dt")
    private LocalDateTime addDt;

    public void setFile(String file){
        this.file = file;
    }

    public void updateBanner(BannerInput param){
        this.name = param.getName();
        this.url = param.getUrl();
        this.target = param.getTarget();
        this.order = param.getOrder();
        this.openYN = param.isOpenYn();
    }
}
