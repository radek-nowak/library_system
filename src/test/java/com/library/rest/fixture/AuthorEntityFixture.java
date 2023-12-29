package com.library.rest.fixture;

import com.library.db.entity.AuthorEntity;
import com.library.db.entity.BookEntity;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.With;

@With
@AllArgsConstructor
@NoArgsConstructor
public class AuthorEntityFixture {
  private Long id = null;
  private String name = "name";
  private Set<BookEntity> books = new HashSet<>();
  private UUID technicalId = UUID.randomUUID();

  public AuthorEntity build() {
    return new AuthorEntity(id, name, books, technicalId);
  }
}
