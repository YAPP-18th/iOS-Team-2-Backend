package com.yapp.user.infra.uploader;

import com.amazonaws.AmazonServiceException;
import com.amazonaws.SdkClientException;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.DeleteObjectRequest;
import com.amazonaws.services.s3.model.PutObjectRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

@Slf4j
@RequiredArgsConstructor
@Component
public class S3Uploader implements Uploader {

    private final static String TEMP_FILE_PATH = "src/main/resources/";

    private final AmazonS3Client amazonS3Client;

    @Value("${cloud.aws.s3.bucket}")
    public String bucket;

    public String upload(MultipartFile multipartFile, String dirName) {
        if (multipartFile.isEmpty()) {
            return "";
        }
        File convertedFile = null;
        try {
            convertedFile = convert(multipartFile);
        } catch (IOException e) {
            log.error("S3에 저장되지 않았습니다. {}", e.getMessage());
        }
        return upload(convertedFile, dirName);
    }

    private String upload(File uploadFile, String dirName) {
        try {
            String fileName = dirName + "/" + uploadFile.getName();
            String uploadImageUrl = putS3(uploadFile, fileName);
            removeNewFile(uploadFile);
            return uploadImageUrl;
        } catch (NullPointerException e) {
            throw new NullPointerException("보낸 데이터가 존재하지 않습니다.");
        }

    }

    private String putS3(File uploadFile, String fileName) {
        amazonS3Client.putObject(new PutObjectRequest(bucket, fileName, uploadFile).withCannedAcl(CannedAccessControlList.PublicRead));
        return amazonS3Client.getUrl(bucket, fileName).toString();
    }

    public void deleteS3(String url) {
        try {
            String[] split = url.split("/");
            StringBuilder keyBuilder = new StringBuilder(split[3]);
            for (int i = 4; i < split.length; i++) {
                keyBuilder.append("/" + split[i]);
            }
            DeleteObjectRequest deleteObjectRequest = new DeleteObjectRequest(this.bucket, keyBuilder.toString());
            amazonS3Client.deleteObject(deleteObjectRequest);
        } catch (AmazonServiceException e) {
            e.printStackTrace();
        } catch (SdkClientException e) {
            e.printStackTrace();
        }
    }

    private void removeNewFile(File targetFile) {
        if (targetFile.delete()) {
            return;
        }
        log.info("임시 파일이 삭제 되지 못했습니다. 파일 이름: {}", targetFile.getName());
    }

    private File convert(MultipartFile file) throws IOException {
        File convertFile = new File(file.getOriginalFilename());
        if (convertFile.createNewFile()) {
            try (FileOutputStream fos = new FileOutputStream(convertFile)) {
                fos.write(file.getBytes());
            }
            return convertFile;
        }
        throw new IllegalArgumentException(String.format("파일 변환이 실패했습니다. 파일 이름: %s", file.getName()));
    }

}
