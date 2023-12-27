package com.library.rest;

import com.library.book.Book;
import com.library.openapi.model.BookObject;
import com.library.openapi.model.FindBooks200Response;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper
public interface RequestMapper {
    default FindBooks200Response toDto(List<Book> books) {
        FindBooks200Response response = new FindBooks200Response();
        response.setContent(books.stream().map(this::toDto).toList());
        return response;
    }

    BookObject toDto(Book book);
}
