package tp.enistore.service;

import java.util.ArrayList;
import java.util.List;

public class ServiceResponse<T> {

    public int code;
    public String message;
    public T data;

    ServiceResponse(int code, String message, T data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    public ServiceResponse() {

    }

}
