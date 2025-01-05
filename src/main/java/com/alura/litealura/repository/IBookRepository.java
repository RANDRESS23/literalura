package com.alura.litealura.repository;

import com.alura.litealura.entity.BookEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface IBookRepository extends JpaRepository<BookEntity, Long> {
    Optional<BookEntity> findByTitle(String title);
    List<BookEntity> findByAuthors_Name(String name);

    @Query("SELECT b FROM BookEntity b JOIN b.languages l WHERE l.language = :language")
    List<BookEntity> findBooksByLanguage(@Param("language") String language);
}
