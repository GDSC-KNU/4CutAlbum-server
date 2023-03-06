package GDSC.FirstProject.s3;

import com.amazonaws.services.s3.transfer.MultipleFileDownload;
import com.amazonaws.services.s3.transfer.TransferManager;
import com.amazonaws.services.s3.transfer.TransferProgress;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;

@Slf4j
@RequiredArgsConstructor
@Service
public class S3Downloader {
    private final TransferManager transferManager;

    @Value("${cloud.aws.s3.bucket}")
    private String bucket;

    /** prefix: s3에서 다운 받을 디렉터리 이름, localDirectoryPath: 다운 받을 로컬 폴더 주소 */
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
}
