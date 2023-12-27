package com.library.book;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BookService {

    private final BookStorage bookStorage;
    private final BookMapper mapper;

    public List<Book> findAll() {
        return bookStorage.findAll().stream().map(mapper::toDomain).toList();
    }
}
