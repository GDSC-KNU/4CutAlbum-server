package GDSC.FirstProject.s3;

import java.util.Base64;
import java.io.FileOutputStream;

public class Base64ImgDecoder {
    public static boolean decode(String base64Img, String fileName) {
        // base64 문자열의 data:image/jpeg;base64 부분 제거
        String[] parts = base64Img.split(",");
        byte[] imageBytes = Base64.getDecoder().decode(parts[1]);

        try (FileOutputStream fos = new FileOutputStream(fileName)) {
            fos.write(imageBytes);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }
}