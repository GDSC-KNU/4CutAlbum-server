package GDSC.FirstProject.s3;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.DeleteObjectRequest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;
@SpringBootTest
public class S3DeleterTest {
    @Autowired
    private S3Uploader s3Uploader;

    @Autowired
    private S3Deleter s3Deleter;

    @Autowired
    private AmazonS3 amazonS3;

    @Value("${cloud.aws.s3.bucket}")
    private String bucket;

    @Test
    void S3DeleteTest() {
        //given
        String base64Image = "";
        String fileName = "TestImage.png";
        s3Uploader.upload(base64Image, fileName);

        //when
        amazonS3.deleteObject(new DeleteObjectRequest(bucket, fileName));

        //then
        assertThat(amazonS3.doesObjectExist(bucket, fileName)).isFalse();
    }
}
