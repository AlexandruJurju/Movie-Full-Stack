package com.example.backend.exception;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.NoSuchElementException;

//@ControllerAdvice
//@Order(Ordered.HIGHEST_PRECEDENCE)
public class RestExceptionHandler extends ResponseEntityExceptionHandler {
//
//    // TODO: remove HttpServletRequest, use just exception
//    @ExceptionHandler(MovieInvalidIdException.class)
//    public ResponseEntity<Object> handleMovieInvalidIdException(HttpServletRequest request, MovieInvalidIdException e) {
//        ErrorResponse response = new ErrorResponse(HttpStatus.NOT_FOUND,
//                "The row for address is not existent: " + request.getRequestURI());
//        return new ResponseEntity<>(response, response.getStatus());
//    }
//
//    @ExceptionHandler(NoSuchElementException.class)
//    public ResponseEntity<Object> handleNoSuchElementException(HttpServletRequest request, NoSuchElementException e) {
//        ErrorResponse response = new ErrorResponse(HttpStatus.NOT_FOUND,
//                "The row for address is not existent: " + request.getRequestURI());
//        return new ResponseEntity<>(response, response.getStatus());
//    }
//
//    @ExceptionHandler(HttpServerErrorException.InternalServerError.class)
//    public ResponseEntity<Object> handleInternalServerError(HttpServletRequest request, HttpServerErrorException.InternalServerError e) {
//        ErrorResponse response = new ErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR,
//                "Internal server error for: " + request.getRequestURI());
//        return new ResponseEntity<>(response, response.getStatus());
//    }
}
