package GDSC.FirstProject.s3;

import java.util.Base64;
import java.io.FileOutputStream;

public class Base64ImgDecoder {
    public static boolean decode(String base64Img, String fileName) {
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