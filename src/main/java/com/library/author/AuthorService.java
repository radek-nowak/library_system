package com.library.author;

import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthorService {

  private final AuthorStorage authorStorage;

  public UUID addAuthor(Author author) {
    return authorStorage.addAuthor(author);
  }
}
