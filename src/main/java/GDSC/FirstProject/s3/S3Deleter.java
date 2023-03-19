package GDSC.FirstProject.s3;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.DeleteObjectRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.File;

@Slf4j
@RequiredArgsConstructor
@Service
public class S3Deleter {
    private final AmazonS3 amazonS3Client;

    @Value("${cloud.aws.s3.bucket}")
    private String bucket;

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
}
