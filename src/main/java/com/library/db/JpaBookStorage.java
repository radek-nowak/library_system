package com.library.db;

import com.library.book.BookStorage;
import com.library.db.entity.BookEntity;
import com.library.db.entity.BookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class JpaBookStorage implements BookStorage {

    private final BookRepository bookRepository;

    @Override
    public List<BookEntity> findAll() {
        return bookRepository.findAll();
    }
}