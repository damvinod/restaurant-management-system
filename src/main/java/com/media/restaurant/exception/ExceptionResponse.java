package com.media.restaurant.exception;

import java.util.Date;

public class ExceptionResponse {

  private String errorCode;
  private Date timestamp;
  private String errorMessage;
  private String details;

  /**
   * Constructor used for setting the exception details to the end user.
   *
   */
  public ExceptionResponse(Date timestamp, String errorMessage, String details) {
    super();
    this.timestamp = timestamp;
    this.errorMessage = errorMessage;
    this.details = details;
  }

  /**
   * Constructor used for setting the exception details to the end user.
   *
   */
  public ExceptionResponse(Date timestamp, String errorMessage, String details, String errorCode) {
    super();
    this.timestamp = timestamp;
    this.errorMessage = errorMessage;
    this.details = details;
    this.errorCode = errorCode;
  }

  public String getDetails() {
    return details;
  }

  public String getErrorCode() {
    return errorCode;
  }

  public String getErrorMessage() {
    return errorMessage;
  }

  public Date getTimestamp() {
    return timestamp;
  }

  public void setDetails(String details) {
    this.details = details;
  }

  public void setErrorCode(String errorCode) {
    this.errorCode = errorCode;
  }

  public void setErrorMessage(String errorMessage) {
    this.errorMessage = errorMessage;
  }

  public void setTimestamp(Date timestamp) {
    this.timestamp = timestamp;
  }
}
