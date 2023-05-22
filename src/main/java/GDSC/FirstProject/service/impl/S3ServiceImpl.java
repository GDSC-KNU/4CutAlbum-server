package GDSC.FirstProject.service.impl;

import GDSC.FirstProject.service.S3Service;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.DeleteObjectRequest;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.amazonaws.services.s3.transfer.MultipleFileDownload;
import com.amazonaws.services.s3.transfer.TransferManager;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileOutputStream;
import java.util.Base64;

@Slf4j
@RequiredArgsConstructor
@Service
public class S3ServiceImpl implements S3Service {

    private final AmazonS3 amazonS3Client;

    private final TransferManager transferManager;

    @Value("${cloud.aws.s3.bucket}")
    private String bucket;

    @Override
    public String upload(String base64, String fileName) {
        boolean result = decode(base64, fileName);
        System.out.println(result);
        File uploadFile = new File(fileName);

        amazonS3Client.putObject(new PutObjectRequest(bucket, fileName, uploadFile).withCannedAcl(CannedAccessControlList.PublicRead));
        // remove new temp file
        if (uploadFile.delete()) {
            log.info(uploadFile.getName() + "로컬 이미지 파일이 삭제 되었습니다.");
        } else {
            log.info(uploadFile.getName() + "로컬 이미지 파일이 삭제 되지 못했습니다.");
        }

        return amazonS3Client.getUrl(bucket, fileName).toString();
    }

    @Override
    public void downloadDirectory(String prefix, String localDirectoryPath) {
        File localDirectory = new File(localDirectoryPath);

        try {
            MultipleFileDownload downloader = transferManager.downloadDirectory(
                    bucket, prefix, localDirectory);
            downloader.waitForCompletion();


            log.info(downloader.getDescription());
        } catch (Exception e) {
            log.info(e.getMessage());
        }
    }

    @Override
    public void delete(String key) {
        if (amazonS3Client.doesObjectExist(bucket, key)) {
            try {
                amazonS3Client.deleteObject(new DeleteObjectRequest(bucket, key));
                log.info(key + ": s3 객체 삭제에 성공했습니다.");
            }
            catch (Exception e) {
                log.info(key + ": s3 객체 삭제에 실패했습니다.");
                log.info(e.getMessage());
            }
        }
    }

    @Override
    public boolean decode(String base64Img, String fileName) {
        // 이미지 base64 문자열만 바로 받아와서 디코딩
        byte[] imageBytes = Base64.getDecoder().decode(base64Img);

        try (FileOutputStream fos = new FileOutputStream(fileName)) {
            fos.write(imageBytes);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }
}
