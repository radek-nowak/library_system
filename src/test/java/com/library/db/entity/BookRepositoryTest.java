package com.library.db.entity;

import static org.assertj.core.api.Assertions.assertThat;

import com.library.rest.fixture.BookEntityFixture;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@DataJpaTest
class BookRepositoryTest {

  @Autowired BookRepository repository;

  @Test
  void shouldFindSavedBook() {
    // given

    BookEntity book = repository.save(new BookEntityFixture().build());

    // when
    List<BookEntity> books = repository.findAll();

    // then
    assertThat(books)
        .hasSize(1)
        .satisfies(
            bookEntities -> {
              BookEntity bookEntity = bookEntities.get(0);
              assertThat(bookEntity.getTitle()).isEqualTo(book.getTitle());
            });
  }
}
