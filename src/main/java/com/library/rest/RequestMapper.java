package com.library.rest;

import com.library.book.Book;
import com.library.openapi.model.BookObject;
import com.library.openapi.model.FindBooks200Response;
import java.util.List;
import org.mapstruct.Mapper;

@Mapper
public interface RequestMapper {
  default FindBooks200Response toDto(List<Book> books) {
    FindBooks200Response response = new FindBooks200Response();
    response.setContent(books.stream().map(this::toDto).toList());
    return response;
  }

  BookObject toDto(Book book);
}
