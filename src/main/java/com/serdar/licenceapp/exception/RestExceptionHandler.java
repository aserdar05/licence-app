package com.serdar.licenceapp.exception;

import com.serdar.licenceapp.commands.ResponseCommand;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice(annotations = RestController.class)
@Order(1) //Diğer exceptionHandler metoduna göre öncelikli olması için
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

    //Invalid input data for the Rest service
    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        ResponseCommand command = new ResponseCommand(false, ex.getBindingResult().toString(), status.value());
        return new ResponseEntity(command, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ResponseCommand> exceptionHandler(Exception exception){
        ResponseCommand command = new ResponseCommand(false, exception.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR.value());
        return new ResponseEntity(command, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
