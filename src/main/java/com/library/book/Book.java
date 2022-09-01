package com.library.book;

import javax.persistence.*;

@Entity
@Table(name = "books")
public class Book {

    @Id
    @SequenceGenerator(
            name = "book_sequence",
            sequenceName = "book_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "book_sequence"
    )
    @Column(name = "book_id")
    private Long bookId;

    private String title;
    private String author;

    /**
     * An empty constructor. For the sake of JPA.
     */
    public Book() {
    }

    /**
     * This constructor is used for instantiating a Book object
     * which is already registered in a database.
     *
     * @param bookId book ID.
     */
    public Book(Long bookId) {
        this.bookId = bookId;
    }

    /**
     * This is a main constructor of this class. It is used when
     * creating new books. There is no need to pass book ID, since
     * it is generated automatically.
     *
     * @param title  book title.
     * @param author book author.
     */
    public Book(String title, String author) {
        this.title = title;
        this.author = author;
    }

    /**
     * Returns a book's ID.
     *
     * @return book ID.
     */
    public Long getBookId() {
        return bookId;
    }

    /**
     * Returns a book's title.
     *
     * @return book title.
     */
    public String getTitle() {
        return title;
    }

    /**
     * Sets a book title.
     *
     * @param title book title.
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * Returns a book's author.
     *
     * @return book author.
     */
    public String getAuthor() {
        return author;
    }

    /**
     * Sets a book author.
     *
     * @param author an author of the book.
     */
    public void setAuthor(String author) {
        this.author = author;
    }

    @Override
    public String toString() {
        return "Book{" +
                "bookId=" + bookId +
                ", title='" + title + '\'' +
                ", author='" + author + '\'' +
                '}';
    }
}
