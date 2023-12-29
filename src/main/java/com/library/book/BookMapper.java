package com.library.book;

import com.library.db.entity.BookEntity;
import java.util.Set;
import org.mapstruct.Mapper;

@Mapper
public interface BookMapper {

  Book toDomain(BookEntity entity);

  Set<BookEntity> toEntity(Set<Book> books);

  default BookEntity toEntity(Book book) {
    return new BookEntity(book.id(), book.title());
  }
}
