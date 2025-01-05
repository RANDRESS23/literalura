package com.alura.litealura.repository;

import com.alura.litealura.entity.LanguageEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ILanguageRepository extends JpaRepository<LanguageEntity, Long> {
    Optional<LanguageEntity> findByLanguage(String language);
}
