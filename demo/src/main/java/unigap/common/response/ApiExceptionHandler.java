package unigap.common.response;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;


@ControllerAdvice
public class ApiExceptionHandler extends ResponseEntityExceptionHandler {
    @ExceptionHandler(value = ApiException.class)
    public ResponseEntity<Object> handleApiException(ApiException e) {
        return responseEntity(e.getErrorCode(), e.getMessage(), e.getHttpStatus());
    }

    private ResponseEntity<Object> responseEntity(Integer errorCode, String msg, HttpStatus httpStatus) {
        return new ResponseEntity<>(
                ApiResponse.builder()
                        .errorCode(errorCode)
                        .statusCode(httpStatus.value())
                        .message(msg)
                        .build(),
                httpStatus);
    }
}
