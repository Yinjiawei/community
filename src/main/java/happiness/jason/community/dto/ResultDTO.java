package happiness.jason.community.dto;

import happiness.jason.community.exception.CustomizeErrorCode;
import happiness.jason.community.exception.CustomizeException;
import lombok.Data;
import org.springframework.web.bind.annotation.ExceptionHandler;

@Data
public class ResultDTO<T> {
    private Integer code;
    private String message;
    private T data;

    public static ResultDTO errorOf(Integer code, String message) {
        ResultDTO resultDTO = new ResultDTO();
        resultDTO.code = code;
        resultDTO.message = message;

        return resultDTO;
    }

    public static ResultDTO errorOf(CustomizeErrorCode errorCode) {
        return errorOf(errorCode.getCode(), errorCode.getMessage());
    }

    public static ResultDTO errorOf(CustomizeException ex) {
        return errorOf(ex.getCode(), ex.getMessage());
    }

    public static ResultDTO okOf() {
        ResultDTO resultDTO = new ResultDTO();
        resultDTO.code = 200;
        resultDTO.message = "请求成功";
        return resultDTO;
    }

    public static <T> ResultDTO okOf(T t){
        ResultDTO resultDTO = new ResultDTO();
        resultDTO.code = 200;
        resultDTO.message = "请求成功";
        resultDTO.setData(t);
        return resultDTO;
    }
}
