package cn.fc.opentv.exception;

/**
 * @author fucheng
 * @date 2023/2/8
 */
public class OpenApiException extends RuntimeException{
    public OpenApiException(String msg) {
        super(msg);
    }

    public OpenApiException(Throwable throwable) {
        super(throwable.getMessage());
    }
}
