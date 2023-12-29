package com.library.author;

import com.library.book.Book;
import java.util.Objects;
import java.util.Set;
import java.util.UUID;

public record Author(Long id, String name, Set<Book> books, UUID technicalId) {
  public void addBook(Book book) {
    if (!books.contains(book)) {
      books.add(book);
      book.addAuthor(this);
    }
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    Author author = (Author) o;
    return Objects.equals(id, author.id)
        && Objects.equals(name, author.name)
        && Objects.equals(technicalId, author.technicalId);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, name, technicalId);
  }

  @Override
  public String toString() {
    return "Author{" + "id=" + id + ", name='" + name + '\'' + ", technicalId=" + technicalId + '}';
  }
}
