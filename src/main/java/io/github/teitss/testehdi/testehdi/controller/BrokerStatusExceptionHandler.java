package io.github.teitss.testehdi.testehdi.controller;

import org.springframework.core.codec.DecodingException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.reactive.function.client.WebClientResponseException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import io.github.teitss.testehdi.testehdi.error.InactiveBrokerException;
import io.github.teitss.testehdi.testehdi.error.PrettyError;


@ControllerAdvice(assignableTypes = BrokerStatusController.class)
public class BrokerStatusExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(InactiveBrokerException.class)
    protected ResponseEntity<Object> handleInactiveBroker(InactiveBrokerException ex) {
        return new ResponseEntity<>(new PrettyError(400, HttpStatus.BAD_REQUEST, ex.getMessage()), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(WebClientResponseException.class)
    protected ResponseEntity<Object> handleWebClientResponse(WebClientResponseException ex) {
        return new ResponseEntity<>(new PrettyError(ex.getRawStatusCode(), ex.getStatusCode(), ex.getMessage()), ex.getStatusCode());
    }

    @ExceptionHandler(DecodingException.class)
    protected ResponseEntity<Object> handleDecodingException(DecodingException ex) {
        return new ResponseEntity<>(new PrettyError(400, HttpStatus.BAD_REQUEST, ex.getCause().getMessage()), HttpStatus.BAD_REQUEST);
    }
    
}
