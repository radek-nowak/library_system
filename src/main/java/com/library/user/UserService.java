package com.library.user;

import com.library.book.Book;
import com.library.book.BookRepository;
import com.library.book.exception.BookNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final BookRepository bookRepository;

    @Autowired
    public UserService(UserRepository userRepository, BookRepository bookRepository) {
        this.userRepository = userRepository;
        this.bookRepository = bookRepository;
    }

    /**
     * Gets a list of all users recorded in the database.
     *
     * @return list of users.
     */
    public List<User> getUsers() {
        return userRepository.findAll();
    }

    /**
     * Gets a user by userId. If user does not exist, an exception is thrown.
     *
     * @param userId user's ID.
     * @return user.
     */
    public Optional<User> getUser(Long userId) {
        boolean exists = userRepository.existsById(userId);
        if (!exists) {
            throw new IllegalStateException("User with ID: " + userId + " does not exist!");
        }
        return userRepository.findById(userId);
    }

    /**
     * Borrows books. In other words, this method adds books to the
     * user's book list. Books can be added by specifying bookId.
     *
     * @param userId user's ID.
     * @param books  list of books to borrow.
     */
    @Transactional
    public void borrowBooks(Long userId, List<Book> books) {
        List<Book> usersBooks = userRepository.getReferenceById(userId).getBookList();
        for (Book book : books) {
            if (!bookRepository.existsById(book.getBookId())) {
                throw new BookNotFoundException(book.getBookId());
            }
            usersBooks.add(book);
            if (usersBooks.size() > 10) {
                throw new IllegalStateException("Limit of books to borrow has been reached!");
            }
        }
    }

    /**
     * Returns books. In other words, this method removes books from
     * the user's book list. Removing books can be performed by specifying
     * book ID.
     *
     * @param userId user's ID.
     * @param books  list of books to return.
     */
    @Transactional
    public void returnBooks(Long userId, List<Book> books) {
        List<Book> usersBooks = userRepository.getReferenceById(userId).getBookList();
        List<Long> userBookListIds = usersBooks.stream()
                .map(Book::getBookId)
                .toList();

        for (Book book : books) {
            if (!userBookListIds.contains(book.getBookId())) {
                throw new BookNotFoundException(book.getBookId());
            }
            usersBooks.remove(books.indexOf(book));
        }
    }
}
