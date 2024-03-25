package unigap.common.response;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.apache.log4j.Logger;
import org.springframework.http.HttpStatus;


@Data
@EqualsAndHashCode(callSuper = true)
public class ApiException extends RuntimeException {

    private static final Logger logger1 = Logger.getLogger(ApiException.class);
    private Integer errorCode;
    private HttpStatus httpStatus;

    public ApiException(Integer errorCode, HttpStatus httpStatus, String message) {
        super(message);
        this.errorCode = errorCode;
        this.httpStatus = httpStatus;
    }
}
