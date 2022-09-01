package com.library.book;

import com.library.book.exception.BookNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class BookService {

    private final BookRepository bookRepository;

    @Autowired
    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    /**
     * Returns a list of all books in the database.
     *
     * @return list of all books.
     */
    public List<Book> books() {
        return bookRepository.findAll();
    }

    /**
     * Adds new book to the database. Passed bookId argument will be overridden
     * by ID generated by the sequence. Title and author are sufficient.
     *
     * @param book book to register.
     */
    public void addNewBook(Book book) {
        bookRepository.save(book);
    }

    /**
     * Deletes a book by specifying ID. If the ID is not present in the database,
     * an exception is thrown.
     *
     * @param bookId book ID.
     */
    public void deleteById(Long bookId) {
        boolean exists = bookRepository.existsById(bookId);
        if (!exists) {
            throw new BookNotFoundException(bookId);
        }
        bookRepository.deleteById(bookId);
    }

    /**
     * Updates book data.
     *
     * @param bookId      ID of the book to update.
     * @param updatedBook a data to update.
     */
    public void updateBook(Long bookId, Book updatedBook) {
        String title = updatedBook.getTitle();
        String author = updatedBook.getAuthor();

        // Check if the book exists in a database.
        Book book = bookRepository
                .findById(bookId)
                .orElseThrow(() -> new BookNotFoundException(bookId));

        if (title != null && title.length() > 0 && !Objects.equals(book.getTitle(), title)) {
            book.setTitle(title);
        }

        if (author != null && author.length() > 0 && !Objects.equals(book.getAuthor(), author)) {
            book.setAuthor(author);
        }

        bookRepository.save(book);
    }
}
