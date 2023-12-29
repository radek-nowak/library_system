package com.library.db;

import com.library.author.Author;
import com.library.author.AuthorMapper;
import com.library.author.AuthorStorage;
import com.library.book.BookMapper;
import com.library.db.entity.AuthorRepository;
import java.util.List;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class JpaAuthorStorage implements AuthorStorage {

  private final AuthorRepository authorRepository;
  private final AuthorMapper authorMapper;
  private final BookMapper bookMapper;

  @Override
  public List<Author> findAll() {
    return authorRepository.findAll().stream().map(authorMapper::toDomain).toList();
  }

  @Override
  public UUID addAuthor(Author author) {
    return authorRepository.save(authorMapper.toEntity(author, bookMapper)).getTechnicalId();
  }

  @Override
  public List<Author> findAuthors(List<UUID> technicalIds) {
    return authorRepository.findByTechnicalIdIsIn(technicalIds).stream()
        .map(authorMapper::toDomain)
        .toList();
  }
}
