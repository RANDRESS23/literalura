package com.alura.litealura.mapper;

import com.alura.litealura.entity.AuthorEntity;
import com.alura.litealura.model.Author;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface IAuthorEntityMapper {
    Author toAuthor(AuthorEntity authorEntity);
    AuthorEntity toAuthorEntity(Author author);
}
