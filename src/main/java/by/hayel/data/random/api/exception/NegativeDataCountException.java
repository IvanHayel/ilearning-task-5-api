package by.hayel.data.random.api.exception;

import java.io.Serial;

public class NegativeDataCountException extends RuntimeException {
  @Serial private static final long serialVersionUID = 1L;

  private static final String DEFAULT_MESSAGE = "Error -> Data count can't be null or negative!";

  public NegativeDataCountException() {
    super(DEFAULT_MESSAGE);
  }
}
