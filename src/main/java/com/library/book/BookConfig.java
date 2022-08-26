package com.library.book;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class BookConfig {

    @Bean
    CommandLineRunner booCommandLineRunner(BookRepository repository) {
        return args -> {
            Book lotr = new Book("Lord of the Rings", "JRR Tolkien");
            Book catch22 = new Book("Catch 22", "Joseph Heller");
            Book gatsby = new Book("The Great Gatsby", "F. Scott Fitzgerald");

            repository.saveAll(List.of(lotr, catch22, gatsby));

        };
    }
}
