package com.library.db.entity;

import jakarta.persistence.*;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.hibernate.collection.spi.PersistentSet;

@Entity
@Table(name = "BOOKS")
@FieldDefaults(level = AccessLevel.PRIVATE)
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class BookEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  Long id;

  String title;

  @ManyToMany(
      mappedBy = "books",
      cascade = {CascadeType.PERSIST, CascadeType.MERGE}, fetch = FetchType.EAGER)
  @ToString.Exclude
  Set<AuthorEntity> authors = new HashSet<>();

  UUID technicalId;

  public BookEntity(Long id, String title, Set<AuthorEntity> authors) {
    this.id = id;
    this.title = title; // todo search by title makes sense
    this.authors = authors;
    this.technicalId = UUID.randomUUID();
  }

  public void addAuthor(AuthorEntity author) {
    if (!authors.contains(author)) {
      authors.add(author);
      author.addBook(this);
    }
  }

  public Set<AuthorEntity> getAuthors() {
    if (authors instanceof PersistentSet && !((PersistentSet) authors).wasInitialized()) {
      authors = new HashSet<>();
    }
    if (authors == null) {
      authors = new HashSet<>();
    }
    return authors;
  }


}
