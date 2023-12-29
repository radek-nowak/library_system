package com.library.author;

import com.library.book.Book;
import java.util.Set;
import java.util.UUID;

public record Author(Long id, String name, Set<Book> books, UUID technicalId) {
  public void addBook(Book book) {
    if (!books.contains(book)) {
      books.add(book);
      book.addAuthor(this);
    }
  }
}
