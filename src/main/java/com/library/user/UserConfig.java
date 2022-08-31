package com.library.user;

import com.library.book.Book;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.util.List;

@Configuration
public class UserConfig {

    @Bean
    CommandLineRunner userCommandLineRunner(UserRepository repository) {
        Book lotr = new Book(1L);
        Book catch22 = new Book(2L);
        Book gatsby = new Book(3L);

        return args -> {

            User bobDylan = new User(
                    "Bob Dylan",
                    "dylan@domain.com",
                    LocalDate.of(1941, 5, 24),
                    List.of(lotr, catch22, gatsby)
            );


            User jimmyPage = new User(
                    "Jimmy Page",
                    "jimmy@domain.com",
                    LocalDate.of(1944, 1, 9)
            );

            repository.saveAll(List.of(bobDylan, jimmyPage));
        };
    }
}
