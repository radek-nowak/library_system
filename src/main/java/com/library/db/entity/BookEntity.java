package com.library.db.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Table(name = "BOOKS")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class BookEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private String title;

  @ManyToMany(
      mappedBy = "books",
      cascade = {CascadeType.PERSIST, CascadeType.MERGE},
      fetch = FetchType.EAGER)
  @ToString.Exclude
  private Set<AuthorEntity> authors = new HashSet<>();

  private String isbn;

  private Integer publicationYear;

  private Genre genre;

  private UUID technicalId;

  public BookEntity(
      Long id,
      String title,
      Set<AuthorEntity> authors,
      String isbn,
      int publicationYear,
      Genre genre) {
    this.id = id;
    this.title = title;
    this.authors = authors;
    this.isbn = isbn;
    this.publicationYear = publicationYear;
    this.genre = genre;
    technicalId = UUID.randomUUID();
  }

  public void addAuthor(AuthorEntity author) {
    if (!authors.contains(author)) {
      authors.add(author);
      author.addBook(this);
    }
  }
}
