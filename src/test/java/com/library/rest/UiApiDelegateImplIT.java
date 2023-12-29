package com.library.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.library.db.entity.AuthorEntity;
import com.library.db.entity.AuthorRepository;
import com.library.db.entity.BookEntity;
import com.library.db.entity.BookRepository;
import com.library.rest.fixture.AuthorEntityFixture;
import com.library.rest.fixture.BookEntityFixture;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import java.util.UUID;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
class UiApiDelegateImplIT {

  @Autowired MockMvc mockMvc;

  @PersistenceContext EntityManager em;

  @Autowired private BookRepository bookRepository;

  @Autowired private AuthorRepository authorRepository;

  @Test
  @Transactional
  void shouldUpdateBooksAuthorsList() throws Exception {
    UUID bookTechnicalId = UUID.randomUUID();
    UUID authorTechnicalId = UUID.randomUUID();
    var bookEntity = new BookEntityFixture().withTechnicalId(bookTechnicalId).build();
    var authorEntity = new AuthorEntityFixture().withTechnicalId(authorTechnicalId).build();

    bookRepository.save(bookEntity);
    authorRepository.save(authorEntity);

    String requestBody = "[" + "\"" + authorTechnicalId + "\"" + "]";

    mockMvc
        .perform(
            put("/api/books/%s/authors".formatted(bookTechnicalId))
                .content(requestBody)
                .contentType(MediaType.APPLICATION_JSON))
        .andDo(print())
        .andExpect(status().isOk())
        .andReturn();

    var foundBookEntity =
        em.createQuery(
                "select b from BookEntity b where technicalId = :technicalId", BookEntity.class)
            .setParameter("technicalId", bookTechnicalId)
            .getSingleResult();

    var foundAuthorEntity =
        em.createQuery(
                "select b from AuthorEntity b where technicalId = :technicalId", AuthorEntity.class)
            .setParameter("technicalId", authorTechnicalId)
            .getSingleResult();

    var joinTableData =
        em.createNativeQuery(
                "select * from authors_books where author_id = :authorId and book_id = :bookId")
            .setParameter("authorId", foundAuthorEntity.getId())
            .setParameter("bookId", foundBookEntity.getId())
            .getResultList();

    assertThat(joinTableData).isNotEmpty();
  }
}
