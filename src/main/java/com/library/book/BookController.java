package com.library.book;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/book")
public class BookController {

    private final BookService bookService;

    @Autowired
    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping
    public List<Book> books() {
        return bookService.books();
    }

    @PostMapping
    public void addNewBook(@RequestBody Book book) {
        bookService.addNewBook(book);
    }

    @PutMapping("{bookId}")
    public void updateBook(@PathVariable Long bookId,
                           @RequestBody Book book){
        bookService.updateBook(bookId, book);
    }

    @DeleteMapping("{bookId}")
    public void deleteById(@PathVariable Long bookId) {
        bookService.deleteById(bookId);
    }

}
