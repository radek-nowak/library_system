package com.library.book;

import com.library.db.entity.BookEntity;
import java.util.List;
import java.util.UUID;

public interface BookStorage {
  List<BookEntity> findAll();

  UUID addBook(Book book);

  void updateBooksAuthorsList(UUID bookTechnicalId, List<UUID> authorTechnicalIds);
}
