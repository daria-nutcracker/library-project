package ru.itgirl.libraryproject.Service;

import ru.itgirl.libraryproject.dto.BookDto;
public interface BookService {
    BookDto getByNameV1 (String name);
}
