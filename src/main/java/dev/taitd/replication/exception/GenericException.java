package dev.taitd.replication.exception;

import lombok.Getter;

@Getter
public class GenericException extends RuntimeException {

  private final String code;

  public GenericException(String code, String message) {
    super(message);
    this.code = code;
  }
}