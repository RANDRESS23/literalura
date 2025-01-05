package com.alura.litealura.mapper;

import com.alura.litealura.entity.LanguageEntity;
import com.alura.litealura.model.Language;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ILanguageEntityMapper {
    Language toLanguage(LanguageEntity languageEntity);
    LanguageEntity toLanguageEntity(Language language);
}
