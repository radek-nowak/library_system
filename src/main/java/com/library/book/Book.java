package com.library.book;

import com.library.author.Author;
import java.util.Set;

public record Book(String title, Set<Author> author) {}
