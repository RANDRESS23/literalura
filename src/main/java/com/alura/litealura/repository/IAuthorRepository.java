package com.alura.litealura.repository;

import com.alura.litealura.entity.AuthorEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface IAuthorRepository extends JpaRepository<AuthorEntity, Long> {
    Optional<AuthorEntity> findByName(String name);

    @Query(value = "SELECT * FROM authors WHERE birth_year <= :year AND death_year >= :year", nativeQuery = true)
    List<AuthorEntity> findAuthorsAliveInYearNative(@Param("year") Long year);
}
