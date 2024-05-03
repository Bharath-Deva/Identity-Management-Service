package com.flyerssoft.ims.rest.advice;

import com.flyerssoft.ims.exception.ImsException;
import com.flyerssoft.ims.exception.BadRequestException;
import com.flyerssoft.ims.exception.NotFoundException;
import com.flyerssoft.ims.exception.UnAuthorizedException;
import com.flyerssoft.ims.exception.UpstreamException;
import com.flyerssoft.ims.model.dto.ImsErrorResponse;
import jakarta.validation.ConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingRequestHeaderException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

/**
 * This method will catch the given exception and send the custom error response
 * to the client.
 */
@RestControllerAdvice
public class ImsControllerAdvice {

  /**
   * This method will catch if the params missed in reques headers and send the
   * custom error response to the client.
   *
   * @return AmsErrorResponse object which is return the message for bad request.
   *
   */
  @ExceptionHandler(MissingRequestHeaderException.class)
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  @ResponseBody
  public ImsErrorResponse missingRequestHeadersException(final MissingRequestHeaderException ex) {
    return new ImsErrorResponse(HttpStatus.BAD_REQUEST.value(), ex.getMessage());
  }

  /**
   * This method will catch if the params missed in query and send the custom
   * error response to the client.
   *
   * @param ex - request params exception
   *
   * @return new Custom error response
   *
   */
  @ExceptionHandler(MissingServletRequestParameterException.class)
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  @ResponseBody
  public ImsErrorResponse
         missingRequestParamsException(MissingServletRequestParameterException ex) {
    return new ImsErrorResponse(HttpStatus.BAD_REQUEST.value(), ex.getMessage());
  }

  /**
   * This method will catch if the params constraints exceptions and send the
   * custom error response to the client.
   *
   * @return AmsErrorResponse object which is return the message for bad request.
   *
   */
  @ExceptionHandler(ConstraintViolationException.class)
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  @ResponseBody
  public ImsErrorResponse constraintViolationException(ConstraintViolationException ex) {
    return new ImsErrorResponse(HttpStatus.BAD_REQUEST.value(), ex.getMessage());
  }

  /**
   * This method will catch the Un-authorized exception and send the custom error
   *    * response to the client.
   *
   * @param ex - unauthorized exception.
   * @return new custom error response unauthorized.
   */
  @ExceptionHandler(UnAuthorizedException.class)
  @ResponseStatus(HttpStatus.UNAUTHORIZED)
  @ResponseBody
  public ImsErrorResponse unauthorizedException(UnAuthorizedException ex) {
    return new ImsErrorResponse(HttpStatus.UNAUTHORIZED.value(),
        ex.getMessage());
  }

  /**
   * This method will catch the Bad Request exception and send the custom error
   * response to the client.
   *
   * @param ex - not found exception
   * @return - new custom error response.
   */
  @ExceptionHandler(NotFoundException.class)
  @ResponseStatus(HttpStatus.NOT_FOUND)
  @ResponseBody
  public ImsErrorResponse notFoundException(
      NotFoundException ex) {
    return new ImsErrorResponse(
        HttpStatus.NOT_FOUND.value(),
        ex.getMessage());
  }

  /**
   * This method will catch the Bad Request exception and send the custom error
   * response to the client.
   *
   * @return AmsErrorResponse object which is return the message for bad request.
   *
   */
  @ExceptionHandler(BadRequestException.class)
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  @ResponseBody
  public ImsErrorResponse badRequestException(BadRequestException ex) {
    return new ImsErrorResponse(HttpStatus.BAD_REQUEST.value(), ex.getMessage());
  }

  /**
   * This method will catch the UpstreamException exception.
   *
   * @return send the custom error response
   *         This method will catch the upstreamException exception and send the
   *         custom
   *         error response
   *         to the client.
   */
  @ExceptionHandler(UpstreamException.class)
  @ResponseBody
  @ResponseStatus(HttpStatus.GATEWAY_TIMEOUT)
  public ImsErrorResponse upstreamException(UpstreamException ex) {
    return new ImsErrorResponse(HttpStatus.GATEWAY_TIMEOUT.value(), ex.getMessage());
  }

  /**
   * This method will catch the argument mismatch exception and send the custom
   * error response to the client.
   *
   * @return AmsErrorResponse object which is return the message for bad request.
   *
   */
  @ExceptionHandler(MethodArgumentTypeMismatchException.class)
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  @ResponseBody
  public ImsErrorResponse methodArguementMismatchException(MethodArgumentTypeMismatchException ex) {
    return new ImsErrorResponse(
        HttpStatus.GATEWAY_TIMEOUT.value(),
        ex.getMessage());
  }

  /**
   * This method will catch the illegal argument exception.
   *
   * @param ex ex
   * @return proper error response
   */
  @ExceptionHandler(IllegalArgumentException.class)
  @ResponseBody
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  public ImsErrorResponse handleIllegalArgumentException(ImsException ex) {
    return new ImsErrorResponse(HttpStatus.BAD_REQUEST.value(),
        ex.getMessage());
  }

  /**
   * This method will catch the Exception
   * and send the custom error response
   * to the client.
   */
  @ExceptionHandler(Exception.class)
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  @ResponseBody
  public ImsErrorResponse exception(Exception ex) {
    return new ImsErrorResponse(HttpStatus.BAD_REQUEST.value(), ex.getMessage());
  }

  /**
   * This method will catch the MethodArgumentNotValidException
   * exception and send the custom error response
   * to the client.
   */
  @ExceptionHandler(MethodArgumentNotValidException.class)
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  @ResponseBody
  public ImsErrorResponse handleMethodArgumentNotValidException(
      MethodArgumentNotValidException ex) {
    BindingResult bindingResult = ex.getBindingResult();
    String errorMessage = bindingResult.getFieldError().getField()
        + " " + bindingResult.getFieldError().getDefaultMessage();
    return new ImsErrorResponse(HttpStatus.BAD_REQUEST.value(), errorMessage);
  }
}
