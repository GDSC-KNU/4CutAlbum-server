package GDSC.FirstProject.message;

public enum Status {

    OK(200, "OK");

    int statusCode;
    String code;

    Status(int statusCode, String code) {
        this.statusCode = statusCode;
        this.code = code;
    }
}
