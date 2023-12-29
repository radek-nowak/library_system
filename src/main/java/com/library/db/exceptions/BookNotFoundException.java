package com.library.db.exceptions;

import java.util.UUID;

public class BookNotFoundException extends RuntimeException {
  public BookNotFoundException(UUID technicalId) {
    super("Could not find a book with technical id: %s".formatted(technicalId));
  }
}
