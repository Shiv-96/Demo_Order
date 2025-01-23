package com.order.Exception;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.NoHandlerFoundException;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorMessage> anyExceptionHandler(Exception ex, WebRequest we) {
        ErrorMessage error = new ErrorMessage(LocalDateTime.now(), ex.getMessage(), we.getDescription(false));
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(NoHandlerFoundException.class)
    public ResponseEntity<ErrorMessage> noHandlerFoundExceptionHandler(NoHandlerFoundException ex, WebRequest we) {
        ErrorMessage error = new ErrorMessage(LocalDateTime.now(), ex.getMessage(), we.getDescription(false));
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(MenuItemException.class)
    public ResponseEntity<ErrorMessage> menuItemExceptionHandler(MenuItemException ex, WebRequest we) {
        ErrorMessage error = new ErrorMessage(LocalDateTime.now(), ex.getMessage(), we.getDescription(false));
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(OrderException.class)
    public ResponseEntity<ErrorMessage> orderExceptionHandler(OrderException ex, WebRequest we) {
        ErrorMessage error = new ErrorMessage(LocalDateTime.now(), ex.getMessage(), we.getDescription(false));
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(OrderItemException.class)
    public ResponseEntity<ErrorMessage> orderItemExceptionHandler(OrderItemException ex, WebRequest we) {
        ErrorMessage error = new ErrorMessage(LocalDateTime.now(), ex.getMessage(), we.getDescription(false));
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }
}
