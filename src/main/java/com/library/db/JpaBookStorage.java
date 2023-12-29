package com.library.db;

import com.library.author.AuthorMapper;
import com.library.book.Book;
import com.library.book.BookMapper;
import com.library.book.BookStorage;
import com.library.db.entity.AuthorRepository;
import com.library.db.entity.BookEntity;
import com.library.db.entity.BookRepository;
import com.library.db.exceptions.BookNotFoundException;
import java.util.List;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@RequiredArgsConstructor
public class JpaBookStorage implements BookStorage {

  private final BookRepository bookRepository;
  private final AuthorRepository authorRepository;
  private final BookMapper bookMapper;
  private final AuthorMapper authorMapper;

  @Override
  public List<BookEntity> findAll() {
    return bookRepository.findAll();
  }

  @Override
  public UUID addBook(Book book) {
    return bookRepository.save(bookMapper.toEntity(book)).getTechnicalId();
  }

  @Override
  @Transactional
  public void updateBooksAuthorsList(UUID bookTechnicalId, List<UUID> authorTechnicalIds) {
    var bookEntity =
        bookRepository
            .findByTechnicalId(bookTechnicalId)
            .orElseThrow(() -> new BookNotFoundException(bookTechnicalId));

    var authorEntities = authorRepository.findByTechnicalIdIsIn(authorTechnicalIds);

    authorEntities.forEach(bookEntity::addAuthor);
  }
}
