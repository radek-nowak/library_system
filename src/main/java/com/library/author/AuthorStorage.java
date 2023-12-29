package com.library.author;

import java.util.List;
import java.util.UUID;

public interface AuthorStorage {
  List<Author> findAll();

  UUID addAuthor(Author author);

  List<Author> findAuthors(List<UUID> technicalIds);
}
