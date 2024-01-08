package com.library.book;

import com.library.author.Author;
import java.util.Set;
import java.util.UUID;

public record Book(
    Long id,
    String title,
    Set<Author> authors,
    String isbn,
    Integer publicationYear,
    Genre genre,
    UUID technicalId) {

  public void addAuthor(Author author) {
    if (!authors.contains(author)) {
      authors.add(author);
      author.addBook(this);
    }
  }

  public enum Genre {
    THRILLER,
    SCIENCE_FICTION,
    CRIMINAL
  }
}
