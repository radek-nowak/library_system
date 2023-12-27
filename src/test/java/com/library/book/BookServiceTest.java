package com.library.book;

import static org.assertj.core.api.Assertions.assertThat;

import com.library.db.JpaBookStorage;
import com.library.db.entity.AuthorEntity;
import com.library.db.entity.BookEntity;
import com.library.db.entity.BookRepository;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.context.annotation.Import;

@Import({BookMapperImpl.class})
class BookServiceTest {

  BookService bookService;
  private BookRepository repository;

  @BeforeEach
  void setUp() {
    BookMapper bookMapper = new BookMapperImpl();
    repository = Mockito.mock(BookRepository.class);
    BookStorage bookStorage = new JpaBookStorage(repository);
    bookService = new BookService(bookStorage, bookMapper);
  }

  @Test
  void shouldFindBooks() {
    // given
    String title = "Catch 22";
    AuthorEntity author = new AuthorEntity(null, "Joseph Heller", Collections.emptySet());
    Mockito.when(repository.findAll())
        .thenReturn(List.of(new BookEntity(null, title, Set.of(author))));

    // when
    List<Book> foundBooks = bookService.findAll();

    // then
    assertThat(foundBooks)
        .hasSize(1)
        .satisfies(
            books -> {
              Book book = books.get(0);
              assertThat(book.author()).isEqualTo(author);
              assertThat(book.title()).isEqualTo(title);
            });
  }
}
