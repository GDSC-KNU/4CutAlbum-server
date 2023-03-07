package GDSC.FirstProject.message;

import lombok.Getter;

@Getter
public class ResponseMessage {
    Status status;
    String message;
    Object data;
}
