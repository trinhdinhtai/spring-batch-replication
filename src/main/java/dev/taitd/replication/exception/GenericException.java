package dev.taitd.replication.exception;

import lombok.Getter;

@Getter
public class GenericException extends RuntimeException {

  private static final long serialVersionUID = 1L;

  private final String code;

  public GenericException(String code, String message) {
    super(message);
    this.code = requireCode(code);
  }

  private static String requireCode(String code) {
    if (code == null || code.isBlank()) {
      throw new IllegalArgumentException("Error code must not be null or blank");
    }
    return code;
  }
}
