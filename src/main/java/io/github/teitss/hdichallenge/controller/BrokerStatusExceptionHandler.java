package io.github.teitss.hdichallenge.controller;

import com.fasterxml.jackson.databind.exc.InvalidFormatException;

import org.springframework.core.codec.DecodingException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.reactive.function.client.WebClientResponseException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import io.github.teitss.hdichallenge.error.InactiveBrokerException;
import io.github.teitss.hdichallenge.error.PrettyError;


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
    protected ResponseEntity<Object> handleDecoding(DecodingException ex) {
        return new ResponseEntity<>(new PrettyError(400, HttpStatus.BAD_REQUEST, ex.getCause().getMessage()), HttpStatus.BAD_REQUEST);
    }

    @Override
    protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex,
            HttpHeaders headers, HttpStatus status, WebRequest request) {
                return new ResponseEntity<>(new PrettyError(status.value(), status, ex.getCause().getMessage()), status);
    }

    
    
}
