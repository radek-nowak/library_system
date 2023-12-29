package com.library.rest.fixture;

import com.library.db.entity.AuthorEntity;
import com.library.db.entity.BookEntity;
import java.util.Collections;
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
  private Set<AuthorEntity> authors = Collections.emptySet();
  private UUID technicalId = UUID.randomUUID();

  public BookEntity build() {
    return new BookEntity(id, title, authors, technicalId);
  }
}
