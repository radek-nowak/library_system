package com.library.book;

import static org.assertj.core.api.Assertions.assertThat;

import com.library.author.AuthorMapper;
import com.library.author.AuthorMapperImpl;
import com.library.author.AuthorStorage;
import com.library.db.JpaAuthorStorage;
import com.library.db.JpaBookStorage;
import com.library.db.entity.AuthorEntity;
import com.library.db.entity.AuthorRepository;
import com.library.db.entity.BookEntity;
import com.library.db.entity.BookRepository;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.UUID;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.context.annotation.Import;

@Import({BookMapperImpl.class, AuthorMapperImpl.class})
class BookServiceTest {

  BookService bookService;
  private BookRepository bookRepository;
  private AuthorRepository authorRepository;

  @BeforeEach
  void setUp() {
    BookMapper bookMapper = new BookMapperImpl();
    AuthorMapper authorMapper = new AuthorMapperImpl();
    bookRepository = Mockito.mock(BookRepository.class);
    authorRepository = Mockito.mock(AuthorRepository.class);
    BookStorage bookStorage =
        new JpaBookStorage(bookRepository, authorRepository, bookMapper, authorMapper);
    AuthorStorage authorStorage = new JpaAuthorStorage(authorRepository, authorMapper, bookMapper);
    bookService = new BookService(bookStorage, authorStorage, bookMapper);
  }

  @Test
  void shouldFindBooks() {
    // given
    String title = "Catch 22";
    AuthorEntity author =
        new AuthorEntity(null, "Joseph Heller", Collections.emptySet(), UUID.randomUUID());
    Mockito.when(bookRepository.findAll())
        .thenReturn(List.of(new BookEntity(null, title, Set.of(author), UUID.randomUUID())));

    // when
    List<Book> foundBooks = bookService.findAll();

    // then
    assertThat(foundBooks)
        .hasSize(1)
        .satisfies(
            books -> {
              Book book = books.get(0);
              assertThat(book.authors()).isEqualTo(author);
              assertThat(book.title()).isEqualTo(title);
            });
  }
}
