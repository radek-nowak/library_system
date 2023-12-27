package com.library.author;

import com.library.db.entity.AuthorEntity;
import org.mapstruct.Mapper;

@Mapper
public interface AuthorMapper {

    Author toDomain(AuthorEntity entity);
}
