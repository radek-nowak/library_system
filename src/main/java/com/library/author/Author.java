package com.library.author;

import com.library.book.Book;
import java.util.Set;

public record Author(String name, Set<Book> books) {}
