package com.library.book;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;


@DataJpaTest
class BookRepositoryTest {

    @Autowired
    private BookRepository bookRepository;

    @Test
    void noBooksRegistered_whenNoneAdded_thenNoneRegistered() {
        assertThat(bookRepository.findAll().size()).isEqualTo(0);
    }

    @Test
    void registerNewBook_whenNoBooksRegistered_thenBookRegistered() {
        Book book = new Book(
                "Sherlock Holmes",
                "Arthur Conan Doyle"
        );
        bookRepository.save(book);

        Optional<Book> bookId = bookRepository.findById(book.getBookId());
        boolean exists = bookId.isPresent();

        assertThat(exists).isTrue();
        assertThat(bookRepository.findAll().size()).isEqualTo(1);
    }

    @Test
    void registerListOfBooks_whenBookListPassed_thenAllRegistered() {
        List<Book> books = List.of(
                new Book("Lord of the Rings", "JRR Tolkien"),
                new Book("Sherlock Holmes", "Arthur Conan Doyle"),
                new Book("The Trial", "Franz Kafka")
        );

        bookRepository.saveAll(books);

        assertThat(bookRepository.findAll().size()).isEqualTo(3);
    }

    @Test
    void generateIdAutomatically_whenBookListPassed_thenAllRegistered() {
        List<Book> books = List.of(
                new Book("Lord of the Rings", "JRR Tolkien"),
                new Book("Sherlock Holmes", "Arthur Conan Doyle"),
                new Book("The Trial", "Franz Kafka")
        );

        bookRepository.saveAll(books);
        List<Long> indices = bookRepository.findAll().stream()
                .map(Book::getBookId)
                .toList();

        assertThat(indices.get(0)).isEqualTo(1L);
        assertThat(indices.get(1)).isEqualTo(2L);
        assertThat(indices.get(2)).isEqualTo(3L);
    }

    @Test
    void deleteBookById_whenBookListPassedAndOneDeleted_thenOneDeleted() {
        List<Book> books = List.of(
                new Book("Lord of the Rings", "JRR Tolkien"),
                new Book("Sherlock Holmes", "Arthur Conan Doyle"),
                new Book("The Trial", "Franz Kafka")
        );

        bookRepository.saveAll(books);
        bookRepository.deleteById(books.get(0).getBookId());

        assertThat(bookRepository.findAll().size()).isEqualTo(2);
    }

    @Test
    void deleteBookById_whenBookListPassedAndOneDeleted_thenIdSequenceIsValid() {
        List<Book> books = List.of(
                new Book("Lord of the Rings", "JRR Tolkien"),
                new Book("Sherlock Holmes", "Arthur Conan Doyle"),
                new Book("The Trial", "Franz Kafka")
        );

        bookRepository.saveAll(books);
        bookRepository.deleteById(books.get(1).getBookId());
        List<Long> indices = bookRepository.findAll().stream()
                .map(Book::getBookId)
                .toList();

        assertThat(indices.get(0)).isEqualTo(1);
        assertThat(indices.get(1)).isEqualTo(3);

    }
}