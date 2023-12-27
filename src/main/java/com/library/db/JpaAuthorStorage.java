package com.library.db;

import com.library.author.Author;
import com.library.author.AuthorMapper;
import com.library.author.AuthorStorage;
import com.library.db.entity.AuthorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class JpaAuthorStorage implements AuthorStorage {

    private final AuthorRepository authorRepository;
    private final AuthorMapper mapper;

    @Override
    public List<Author> findAll() {
        return authorRepository.findAll().stream().map(mapper::toDomain).toList();
    }
}
