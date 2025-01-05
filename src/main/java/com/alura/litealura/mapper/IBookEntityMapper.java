package com.alura.litealura.mapper;

import com.alura.litealura.entity.BookEntity;
import com.alura.litealura.model.Book;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface IBookEntityMapper {
    Book toBook(BookEntity bookEntity);
    BookEntity toBookEntity(Book book);
}
