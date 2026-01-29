package book.store.payload.response;

import lombok.Data;

@Data
public class BaseRsp {

    private int statusCode = 200;
    private String message;
    private Object data = "";
}
