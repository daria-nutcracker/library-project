package ru.itgirl.libraryproject.Service;

import ru.itgirl.libraryproject.dto.AuthorDto;
import ru.itgirl.libraryproject.dto.BookCreateDto;
import ru.itgirl.libraryproject.dto.BookDto;
import ru.itgirl.libraryproject.dto.BookUpdateDto;

public interface BookService {

    BookDto getBookById (Long id);
    BookDto getByNameV1 (String name);

    BookDto getByNameV2 (String name);

    BookDto getByNameV3 (String name);

    BookDto createBook (BookCreateDto bookCreateDto);

    BookDto updateBook (BookUpdateDto bookUpdateDto);

    void deleteBook (Long id);
}
