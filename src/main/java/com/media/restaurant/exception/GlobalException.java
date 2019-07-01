package com.media.restaurant.exception;

public class GlobalException extends Exception {

  private static final long serialVersionUID = 1L;

  private String errorCode;
  private String errorMessage;

  /**
   * Constructor used for setting the exception details from controller.
   *
   */
  public GlobalException(String errorCode, String errorMessage) {
    super(errorMessage);
    setErrorMessage(errorMessage);
    setErrorCode(errorCode);
  }

  /**
   * Constructor used for setting the exception details from controller.
   *
   */
  public GlobalException(String errorCode, String errorMessage, Throwable exception) {
    super(errorMessage, exception);
    setErrorMessage(errorMessage);
    setErrorCode(errorCode);
  }

  public String getErrorCode() {
    return errorCode;
  }

  public String getErrorMessage() {
    return errorMessage;
  }

  public void setErrorCode(String errorCode) {
    this.errorCode = errorCode;
  }

  public void setErrorMessage(String errorMessage) {
    this.errorMessage = errorMessage;
  }
}
