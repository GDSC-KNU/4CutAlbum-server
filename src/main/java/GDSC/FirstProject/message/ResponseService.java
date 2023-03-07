package GDSC.FirstProject.message;

import org.springframework.stereotype.Service;

@Service
public class ResponseService {

    public ResponseMessage getSuccessResponse() {
        ResponseMessage response = new ResponseMessage();
        setSuccessResponse(response);
        return response;
    }

    public <T> SingleResponse<T> getSingleResponse(T data) {
        SingleResponse response = new SingleResponse();
        response.data = data;
        setSuccessResponse(response);
        return response;
    }

    public ResponseMessage getErrorResponse(Status status, String message) {
        ResponseMessage response = new ResponseMessage();
        response.status = status;
        response.message = message;
        return response;
    }

    private void setSuccessResponse(ResponseMessage response) {
        response.message = "SUCCESS";
        response.status = Status.OK;
    }
}
