package com.zerobase.fastlms.util;

import com.zerobase.fastlms.banner.entity.Banner;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletContext;
import java.io.File;
import java.io.IOException;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class FileSaveUtil {

    private final ServletContext servletContext;

    public void saveFile(MultipartFile file, Banner banner) throws IOException {
        if(!file.isEmpty()){
            String realPath = servletContext.getRealPath("/img/banner/");

            String fullFilePath = realPath  + UUID.randomUUID() + getExtractName(file);
            file.transferTo(new File(fullFilePath));

            int webappIdx = fullFilePath.lastIndexOf("main/webapp");
            String relativePath = fullFilePath.substring(webappIdx + 11);

            banner.setFile(relativePath);
        }
    }



    private String getExtractName(MultipartFile file){

        String originalFilename = file.getOriginalFilename();

        if(originalFilename == null || !originalFilename.contains(".")){
            return "";
        }

        int position = originalFilename.lastIndexOf(".");

        return originalFilename.substring(position);
    }
}
