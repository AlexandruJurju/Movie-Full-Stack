package com.example.springmovie.exception;


import lombok.extern.slf4j.Slf4j;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@Slf4j
@ControllerAdvice
@Order(Ordered.HIGHEST_PRECEDENCE)
public class GlobalExceptionHandler {

    @ExceptionHandler(UserAlreadyExistsException.class)
    public ResponseEntity<ErrorResponse> handleUserAlreadyExistsException(UserAlreadyExistsException e) {
        log.error("User already exists exception occurred", e);
        ErrorResponse response = new ErrorResponse(HttpStatus.CONFLICT, e.getMessage());
        return new ResponseEntity<>(response, response.getStatus());
    }

    //    @ExceptionHandler(GenreNotFoundException.class)
    //    public ResponseEntity<ErrorResponse> handleGenreNotFoundException(HttpServletRequest request, GenreNotFoundException e) {
    //        log.error("Movie not found exception occurred", e);
    //
    //        ErrorResponse response = new ErrorResponse(HttpStatus.NOT_FOUND, "Cannot find genre for the request: " + request.getRequestURI());
    //        return new ResponseEntity<>(response, response.getStatus());
    //    }


    //    @ExceptionHandler(UserNotFoundException.class)
    //    public ResponseEntity<ErrorResponse> handleUserNotFoundException(HttpServletRequest request, UserNotFoundException e) {
    //        log.error("User not found exception occurred", e);
    //
    //        ErrorResponse response = new ErrorResponse(HttpStatus.NOT_FOUND, "Cannot find user for the request: " + request.getRequestURI());
    //        return new ResponseEntity<>(response, response.getStatus());
    //    }
    //
    //    @ExceptionHandler(MovieNotFoundException.class)
    //    public ResponseEntity<ErrorResponse> handleMovieNotFoundException(HttpServletRequest request, MovieNotFoundException e) {
    //        log.error("Movie not found exception occurred", e);
    //
    //        ErrorResponse response = new ErrorResponse(HttpStatus.NOT_FOUND, "Cannot find movie for the request: " + request.getRequestURI());
    //        return new ResponseEntity<>(response, response.getStatus());
    //    }
    //
    //    @ExceptionHandler(NoSuchElementException.class)
    //    public ResponseEntity<ErrorResponse> handleNoSuchElementException(HttpServletRequest request, NoSuchElementException e) {
    //        log.error("No such element exception occurred", e);
    //
    //        ErrorResponse response = new ErrorResponse(HttpStatus.NOT_FOUND, "The row for address is not existent: " + request.getRequestURI());
    //        return new ResponseEntity<>(response, response.getStatus());
    //    }
    //
    //    @ExceptionHandler(HttpServerErrorException.InternalServerError.class)
    //    public ResponseEntity<ErrorResponse> handleInternalServerError(HttpServletRequest request, HttpServerErrorException.InternalServerError e) {
    //        log.error("Internal server error occurred", e);
    //
    //        ErrorResponse response = new ErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR, "Internal server error for: " + request.getRequestURI());
    //        return new ResponseEntity<>(response, response.getStatus());
    //    }
}