package com.library.db.entity;

import com.library.book.Book;
import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

@Entity
@Table(name = "AUTHORS")
@Getter
@FieldDefaults(level = AccessLevel.PRIVATE)
@AllArgsConstructor
@NoArgsConstructor
public class AuthorEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  Long id;

  String name;

  @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
  @JoinTable(
      name = "authors_books",
      joinColumns = @JoinColumn(name = "book_id"),
      inverseJoinColumns = @JoinColumn(name = "author_id"))
  Set<BookEntity> books = new HashSet<>();

  UUID technicalId;

  public AuthorEntity(Long id, String name, Set<BookEntity> books) {
    this.id = id;
    this.name = name;
    this.books = books;
    this.technicalId = UUID.randomUUID();
  }

  public void addBook(BookEntity book) {
    if (!books.contains(book)) {
      books.add(book);
      book.addAuthor(this);
    }
  }
}
