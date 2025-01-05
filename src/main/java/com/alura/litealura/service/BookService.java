package com.alura.litealura.service;

import com.alura.litealura.entity.BookEntity;
import com.alura.litealura.mapper.IBookEntityMapper;
import com.alura.litealura.model.Book;
import com.alura.litealura.repository.IBookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BookService {
    private final IBookRepository bookRepository;
    private final IBookEntityMapper bookEntityMapper;

    public List<Book> getTotalBooks() {
        List<BookEntity> booksEntity = bookRepository.findAll();
        return booksEntity.stream().map(bookEntityMapper::toBook).toList();
    }

    public Book getBookByTitle(String title) {
        Optional<BookEntity> bookEntity = bookRepository.findByTitle(title);
        return bookEntityMapper.toBook(bookEntity.orElse(null));
    }

    public Book saveBook(Book book) {
        BookEntity bookEntity = bookRepository.save(bookEntityMapper.toBookEntity(book));
        return bookEntityMapper.toBook(bookEntity);
    }

    public String getAuthorBooks(String name) {
        StringBuilder books = new StringBuilder();

        List<BookEntity> authorBooks = bookRepository.findByAuthors_Name(name);

        for (int i = 0; i < authorBooks.size(); i++) {
            books.append(authorBooks.get(i).getTitle());

            if (i != (authorBooks.size() - 1)) books.append(", ");
        }

        return books.toString();
    }

    public List<Book> getBooksByLanguage(String language) {
        List<BookEntity> booksEntity = bookRepository.findBooksByLanguage(language);
        return booksEntity.stream().map(bookEntityMapper::toBook).toList();
    }
}
