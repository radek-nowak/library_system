package com.library.user;

import com.library.book.Book;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "users")
public class User {

    @Id
    @SequenceGenerator(
            name = "user_sequence",
            sequenceName = "user_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "user_sequence"
    )
    @Column(name = "user_id")
    private Long userId;

    private String name;
    private String email;

    /**
     * Date of birth.
     */
    @Column(name = "date_of_birth")
    private LocalDate dob;

    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", referencedColumnName = "user_id")
    @Column(name = "book_list")
    private List<Book> bookList;

    /**
     * An empty constructor. For the sake of JPA.
     */
    public User() {
    }

    /**
     * This constructor is used for creating a new user without a book list.
     * In this case, for example, get request would result with an empty list: "bookList": [].
     *
     * @param name  username.
     * @param email user's email.
     * @param dob   user's date of birth.
     */
    public User(String name, String email, LocalDate dob) {
        this.name = name;
        this.email = email;
        this.dob = dob;
    }

    /**
     * This constructor is used for creating a new user with a list of books.
     *
     * @param name     username.
     * @param email    user's email.
     * @param dob      user's date of birth.
     * @param bookList books list.
     */
    public User(String name, String email, LocalDate dob, List<Book> bookList) {
        this.name = name;
        this.email = email;
        this.dob = dob;
        this.bookList = bookList;
    }

    /**
     * Returns user ID.
     *
     * @return user ID.
     */
    public Long getUserId() {
        return userId;
    }

    /**
     * Returns username.
     *
     * @return username.
     */
    public String getName() {
        return name;
    }

    /**
     * Sets username.
     *
     * @param name username.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Returns user's email.
     *
     * @return user's email.
     */
    public String getEmail() {
        return email;
    }

    /**
     * Sets user's email.
     *
     * @param email user's email.
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Returns user's date of birth.
     *
     * @return user's date of birth.
     */
    public LocalDate getDob() {
        return dob;
    }

    /**
     * Sets user's date of birth.
     *
     * @param dob user's date of birth.
     */
    public void setDob(LocalDate dob) {
        this.dob = dob;
    }

    /**
     * Returns a list of books possessed by the user.
     *
     * @return a list of books.
     */
    public List<Book> getBookList() {
        return bookList;
    }

    /**
     * Sets a list of books possessed by the user.
     *
     * @param bookList a list of books.
     */
    public void setBookList(List<Book> bookList) {
        this.bookList = bookList;
    }

    @Override
    public String toString() {
        return "User{" +
                "userId=" + userId +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", dob=" + dob +
                '}';
    }
}

