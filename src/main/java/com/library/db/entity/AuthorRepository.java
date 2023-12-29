package com.library.db.entity;

import java.util.List;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthorRepository extends JpaRepository<AuthorEntity, Long> {

  List<AuthorEntity> findByTechnicalIdIsIn(List<UUID> technicalIds);
}
