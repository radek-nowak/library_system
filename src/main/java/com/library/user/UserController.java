package com.library.user;

import com.library.book.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "api/users")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public List<User> users() {
        return userService.getUsers();
    }

    @GetMapping("{userId}")
    public Optional<User> getUser(@PathVariable Long userId) {
        return userService.getUser(userId);
    }

    @PutMapping("{userId}")
    public void borrowBooks(@PathVariable Long userId, @RequestBody List<Book> books) {
        userService.borrowBooks(userId, books);
    }

    @DeleteMapping("{userId}")
    public void returnBooks(@PathVariable Long userId, @RequestBody List<Book> books) {
        userService.returnBooks(userId, books);
    }
}
