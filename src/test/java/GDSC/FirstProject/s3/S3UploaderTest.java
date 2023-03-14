package GDSC.FirstProject.s3;

import com.amazonaws.services.s3.AmazonS3;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;


import static org.assertj.core.api.Assertions.assertThat;
@SpringBootTest
class S3UploaderTest {
    @Autowired
    private S3Uploader s3Uploader;

    @Autowired
    private AmazonS3 amazonS3;

    @Value("${cloud.aws.s3.bucket}")
    private String bucket;

    @Test
    void S3UploadTest() {
        //given
        String base64Image = "";
        String fileName = "TestImage.png";

        //when
        String imageUrl = s3Uploader.upload(base64Image, fileName);
        String key = "";

        //then
        assertThat(amazonS3.doesObjectExist(bucket, fileName)).isTrue();
    }
}