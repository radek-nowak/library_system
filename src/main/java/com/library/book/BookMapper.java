package com.library.book;

import com.library.db.entity.BookEntity;
import java.util.Set;
import org.mapstruct.Mapper;

@Mapper
public interface BookMapper {

  Book toDomain(BookEntity entity);

  BookEntity toEntity(Book book);

  Set<BookEntity> toEntity(Set<Book> books);
}
