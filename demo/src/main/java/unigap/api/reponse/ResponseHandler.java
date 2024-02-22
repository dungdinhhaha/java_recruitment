package unigap.api.reponse;

import lombok.Getter;
import lombok.Setter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.HashMap;
import java.util.Map;


public class ResponseHandler {

    public static ResponseEntity<Object> responseBuilder(
         int errorCode  , HttpStatus httpStatus , String message , Object responseObject
    ){
        Map<String,Object> response = new HashMap<>();
        response.put("errorCode",errorCode);
        response.put("httpStatus",httpStatus);
        response.put("message",message);
        response.put("Object",responseObject);
        return  new ResponseEntity<>(response,httpStatus);
    }
}
