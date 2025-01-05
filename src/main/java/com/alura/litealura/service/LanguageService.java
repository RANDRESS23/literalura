package com.alura.litealura.service;

import com.alura.litealura.entity.LanguageEntity;
import com.alura.litealura.mapper.ILanguageEntityMapper;
import com.alura.litealura.model.Language;
import com.alura.litealura.repository.ILanguageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class LanguageService {
    private final ILanguageRepository languageRepository;
    private final ILanguageEntityMapper languageEntityMapper;

    public Language getLanguageByLanguage(String language) {
        Optional<LanguageEntity> languageEntity = languageRepository.findByLanguage(language);
        return languageEntityMapper.toLanguage(languageEntity.orElse(null));
    }

    public Language saveLanguage(Language language) {
        LanguageEntity languageEntity = languageRepository.save(languageEntityMapper.toLanguageEntity(language));
        return languageEntityMapper.toLanguage(languageEntity);
    }
}
