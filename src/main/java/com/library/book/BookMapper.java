package com.library.book;

import com.library.db.entity.BookEntity;
import org.mapstruct.Mapper;

@Mapper
public interface BookMapper {
    Book toDomain(BookEntity entities);
}
