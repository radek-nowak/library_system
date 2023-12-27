package com.library.book;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BookService {

  private final BookStorage bookStorage;
  private final BookMapper mapper;

  public List<Book> findAll() {
    return bookStorage.findAll().stream().map(mapper::toDomain).toList();
  }
}
