package GDSC.FirstProject.message;

import lombok.Getter;

@Getter
public class SingleResponse<T> extends ResponseMessage {
    T data;
}
