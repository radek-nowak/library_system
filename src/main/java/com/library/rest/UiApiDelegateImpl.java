package com.library.rest;

import com.library.book.BookStorage;
import com.library.openapi.model.FindBooks200Response;
import com.library.openapi.rest.UiApiDelegate;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UiApiDelegateImpl implements UiApiDelegate {

    private final BookStorage bookStorage;
    private final RequestMapper requestMapper;

    @Override
    public ResponseEntity<FindBooks200Response> findBooks() {
        return ResponseEntity.ok(requestMapper.toDto(bookStorage.findAll()));
//        return UiApiDelegate.super.findBooks();
    }
}
