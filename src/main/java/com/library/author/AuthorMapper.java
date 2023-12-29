package com.library.author;

import com.library.book.BookMapper;
import com.library.db.entity.AuthorEntity;
import java.util.Set;
import org.mapstruct.Mapper;

@Mapper
public interface AuthorMapper {

  Author toDomain(AuthorEntity entity);

  default AuthorEntity toEntity(Author author, BookMapper bookMapper) {
    return new AuthorEntity(author.id(), author.name(), bookMapper.toEntity(author.books()));
  }

  Set<AuthorEntity> toEntity(Set<Author> authors);
}
