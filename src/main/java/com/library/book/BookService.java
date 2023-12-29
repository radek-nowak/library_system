package com.library.book;

import com.library.author.AuthorStorage;
import java.util.List;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class BookService {

  private final BookStorage bookStorage;
  private final AuthorStorage authorStorage;
  private final BookMapper mapper;

  public List<Book> findAll() {
    return bookStorage.findAll().stream().map(mapper::toDomain).toList();
  }

  public UUID addNewBook(Book book) {
    return bookStorage.addBook(book);
  }

  public void updateBooksAuthorList(UUID bookTechnicalId, List<UUID> UUID) {
    bookStorage.updateBooksAuthorsList(bookTechnicalId, UUID);
    //    Book book = bookStorage.findByTechnicalId(bookTechnicalId);
    //    List<Author> authors = authorStorage.findAuthors(UUID);
    //
    //    authors.forEach(book::addAuthor);
    //    bookStorage.save(book);
  }
}
