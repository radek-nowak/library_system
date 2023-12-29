package com.library.book;

import com.library.author.Author;
import java.util.Objects;
import java.util.Set;
import java.util.UUID;

public record Book(Long id, String title, Set<Author> authors, UUID technicalId) {
  public void addAuthor(Author author) {
    if (!authors.contains(author)) {
      authors.add(author);
      author.addBook(this);
    }
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    Book book = (Book) o;
    return Objects.equals(id, book.id)
        && Objects.equals(title, book.title)
        && Objects.equals(technicalId, book.technicalId);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, title, technicalId);
  }

  @Override
  public String toString() {
    return "Book{" + "id=" + id + ", title='" + title + '\'' + ", technicalId=" + technicalId + '}';
  }
}
