package com.zerobase.fastlms.banner.service.Impl;

import com.zerobase.fastlms.admin.mapper.BannerMapper;
import com.zerobase.fastlms.banner.dto.BannerDto;
import com.zerobase.fastlms.banner.entity.Banner;
import com.zerobase.fastlms.banner.model.BannerInput;
import com.zerobase.fastlms.banner.model.BannerParam;
import com.zerobase.fastlms.banner.repository.BannerRepository;
import com.zerobase.fastlms.banner.service.BannerService;
import com.zerobase.fastlms.util.FileSaveUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BannerServiceImpl implements BannerService {

    private final BannerRepository bannerRepository;
    private final ServletContext servletContext;
    private final FileSaveUtil saveUtil;
    private final BannerMapper bannerMapper;


    @Override
    @Transactional
    public void addBanner(HttpServletRequest request, MultipartFile file, BannerInput param) throws IOException {
        Banner banner = Banner.builder()
                .name(param.getName())
                .url(param.getUrl())
                .target(param.getTarget())
                .order(param.getOrder())
                .openYN(param.isOpenYn())
                .addDt(LocalDateTime.now())
                .build();

        saveUtil.saveFile(file,banner);
        bannerRepository.save(banner);

    }

    @Override
    @Transactional
    public void updateBanner(Long id, MultipartFile file, BannerInput param) {
        Banner findBanner = bannerRepository.findById(id)
                .orElseThrow(() -> new RuntimeException(
                        ("banner를 찾을 수 없습니다. id ->" + id)
                ));

        try{
            if(!file.isEmpty()){
                saveUtil.saveFile(file,findBanner);
            }
        } catch (IOException e){
            throw new RuntimeException("파일을 업데이트 할 수 없습니다.");
        }

        findBanner.updateBanner(param);
    }

    @Override
    @Transactional
    public List<BannerDto> list(BannerParam parameter) {
        long totalCnt = bannerMapper.selectListCount(parameter);

        List<BannerDto> list = bannerMapper.selectList(parameter);
        if(!CollectionUtils.isEmpty(list)){
            int i = 0;
            for(BannerDto dto : list){
                dto.setTotalCnt(totalCnt);
                dto.setSeq(totalCnt - parameter.getPageStart() - i);
                i++;
            }
        }

        return list;
    }

    @Override
    @Transactional
    public boolean del(String idList) {
        if(idList != null && idList.length() > 0){
            String[] ids = idList.split(",");
            for (String x : ids){
                long id = 0L;
                try{
                    id = Long.parseLong(x);
                } catch (Exception e){
                    new RuntimeException( id +"찾을 수 없습니다.");
                }

                if (id > 0){
                    Banner findBanner = bannerRepository.findById(id)
                            .orElseThrow(() -> new RuntimeException("배너를 찾을 수 없습니다."));

                    String fullFilePath = servletContext.getRealPath("") + findBanner.getFile();

                    File file = new File(fullFilePath);
                    file.delete();

                    bannerRepository.deleteById(id);
                }


            }
        }
        return true;
    }

    @Override
    @Transactional
    public List<BannerDto> showBannerMainPage() {

        List<BannerDto> findBanners = bannerRepository.findAll()
                .stream()
                .filter(b -> b.getOpenYN().equals(true))
                .map(BannerDto::fromEntity)
                .sorted((o1, o2) -> o1.getOrder() - o2.getOrder() > 0 ? 1 : -1)
                .collect(Collectors.toList());


        return findBanners;
    }
}
