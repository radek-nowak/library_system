package com.library.db;

import com.library.book.BookStorage;
import com.library.db.entity.BookEntity;
import com.library.db.entity.BookRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class JpaBookStorage implements BookStorage {

  private final BookRepository bookRepository;

  @Override
  public List<BookEntity> findAll() {
    return bookRepository.findAll();
  }
}
