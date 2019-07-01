package com.media.restaurant.exception;

import java.util.Date;
import java.util.stream.Collectors;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import com.media.restaurant.constant.CommonConstant;

@ControllerAdvice
@RestController
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

  /**
   * This method will handle all exceptions.
   *
   * @return
   */
  @ExceptionHandler(Exception.class)
  public final ResponseEntity<Object> handleAllException(Exception ex, WebRequest request) {

    ExceptionResponse exceptionResponse =
        new ExceptionResponse(new Date(), ex.getMessage(), request.getDescription(Boolean.FALSE));

    return new ResponseEntity<>(exceptionResponse, HttpStatus.INTERNAL_SERVER_ERROR);
  }

  /**
   * This method handle Illegal arguments exception.
   *
   * @return
   */
  @ExceptionHandler(IllegalArgumentException.class)
  public final ResponseEntity<Object> handleIllegalArgumentException(IllegalArgumentException ex,
      WebRequest request) {

    ExceptionResponse exceptionResponse =
        new ExceptionResponse(new Date(), ex.getMessage(), request.getDescription(Boolean.FALSE));

    return new ResponseEntity<>(exceptionResponse, HttpStatus.BAD_REQUEST);
  }

  @Override
  protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
      HttpHeaders headers, HttpStatus status, WebRequest request) {

    ExceptionResponse exceptionResponse =
        new ExceptionResponse(new Date(), CommonConstant.VALIDATION_FAILED,
            ex.getBindingResult().getFieldErrors().stream()
            .map((FieldError fieldError) -> fieldError.getDefaultMessage()
                + CommonConstant.FOR_THE_FIELD + fieldError.getField())
            .collect(Collectors.joining(",")));

    return new ResponseEntity<>(exceptionResponse, HttpStatus.BAD_REQUEST);
  }

  /**
   * This method handles the Global API Exception.
   *
   * @return
   */
  @ExceptionHandler(GlobalException.class)
  public final ResponseEntity<Object> handleUserNotFoundException(GlobalException ex,
      WebRequest request) {

    ExceptionResponse exceptionResponse = new ExceptionResponse(new Date(), ex.getErrorMessage(),
        request.getDescription(Boolean.FALSE), ex.getErrorCode());

    return new ResponseEntity<>(exceptionResponse, HttpStatus.BAD_REQUEST);
  }
}
