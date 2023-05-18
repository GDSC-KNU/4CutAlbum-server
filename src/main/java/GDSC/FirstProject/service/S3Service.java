package GDSC.FirstProject.service;

public interface S3Service {
    public String upload(String base64, String fileName);

    public void downloadDirectory(String prefix, String localDirectoryPath);

    public void delete(String key);

    public boolean decode(String base64Img, String fileName);
}
