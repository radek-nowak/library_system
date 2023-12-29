package com.library.db.entity;

import jakarta.persistence.*;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "AUTHORS")
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class AuthorEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private String name;

  @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
  @JoinTable(
      name = "authors_books",
      joinColumns = @JoinColumn(name = "book_id"),
      inverseJoinColumns = @JoinColumn(name = "author_id"))
  private Set<BookEntity> books = new HashSet<>();

  private UUID technicalId;

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
