package com.alura.litealura.service;

import com.alura.litealura.entity.AuthorEntity;
import com.alura.litealura.mapper.IAuthorEntityMapper;
import com.alura.litealura.model.Author;
import com.alura.litealura.repository.IAuthorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthorService {
    private final IAuthorRepository authorRepository;
    private final IAuthorEntityMapper authorEntityMapper;

    public List<Author> getTotalAuthors() {
        List<AuthorEntity> authorEntities = authorRepository.findAll();
        return authorEntities.stream().map(authorEntityMapper::toAuthor).toList();
    }

    public Author getAuthorByName(String name) {
        Optional<AuthorEntity> authorEntity = authorRepository.findByName(name);
        return authorEntityMapper.toAuthor(authorEntity.orElse(null));
    }

    public Author saveAuthor(Author author) {
        AuthorEntity authorEntity = authorRepository.save(authorEntityMapper.toAuthorEntity(author));
        return authorEntityMapper.toAuthor(authorEntity);
    }

    public List<Author> getTotalAuthorsAliveInYear(Long year) {
        List<AuthorEntity> authorAlive = authorRepository.findAuthorsAliveInYearNative(year);
        return authorAlive.stream().map(authorEntityMapper::toAuthor).toList();
    }
}
