package com.library.book;

import com.library.db.entity.BookEntity;
import java.util.List;

public interface BookStorage {
  List<BookEntity> findAll();
}
