package com.zerobase.fastlms.banner.model;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@ToString
public class BannerInput {

    private String name;

    private String url;

    private BannerTarget target;

    private Integer order;

    private boolean openYn;

    //삭제를 위한
    String idList;


}