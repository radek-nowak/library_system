package com.library.rest.fixture;

import com.library.db.entity.AuthorEntity;
import com.library.db.entity.BookEntity;
import com.library.db.entity.Genre;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.With;

@With
@AllArgsConstructor
@NoArgsConstructor
public class BookEntityFixture {

  private Long id = null;
  private String title = "Title";
  private Set<AuthorEntity> authors = new HashSet<>();
  private UUID technicalId = UUID.randomUUID();
  private String ISBN = "978-83-01-00000-1";
  private int publicationYear = 1984;
  private Genre genre = Genre.THRILLER;

  public BookEntity build() {
    return new BookEntity(id, title, authors, ISBN, publicationYear, genre);
  }
}
