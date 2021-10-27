package com.yapp.user.infra.uploader;

import org.springframework.web.multipart.MultipartFile;

public interface Uploader {
    String upload(MultipartFile multipartFile, String dirName);
}
