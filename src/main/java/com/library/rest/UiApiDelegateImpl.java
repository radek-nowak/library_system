package com.library.rest;

import com.library.author.AuthorService;
import com.library.book.BookService;
import com.library.openapi.model.AuthorPostObject;
import com.library.openapi.model.BookPostObject;
import com.library.openapi.model.FindBooks200Response;
import com.library.openapi.rest.UiApiDelegate;
import java.util.List;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UiApiDelegateImpl implements UiApiDelegate {

  private final AuthorService authorService;
  private final BookService bookService;
  private final RequestMapper requestMapper;

  @Override
  public ResponseEntity<FindBooks200Response> findBooks() {
    return ResponseEntity.ok(requestMapper.toDto(bookService.findAll()));
  }

  @Override
  public ResponseEntity<String> addBook(BookPostObject bookPostObject) {
    var book = requestMapper.toDomain(bookPostObject);
    UUID technicalID = bookService.addNewBook(book);
    return ResponseEntity.ok(technicalID.toString());
  }

  @Override
  public ResponseEntity<String> addAuthor(AuthorPostObject authorPostObject) {
    var author = requestMapper.toDomain(authorPostObject);
    UUID technicalId = authorService.addAuthor(author);
    return ResponseEntity.ok(technicalId.toString());
  }

  @Override
  public ResponseEntity<String> updateBooksAuthorList(UUID bookTechnicalId, List<UUID> UUID) {
    bookService.updateBooksAuthorList(bookTechnicalId, UUID);
    return ResponseEntity.ok("UPDATED");
  }
}
