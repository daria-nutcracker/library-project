package ru.itgirl.libraryproject.Service;

import ru.itgirl.libraryproject.dto.BookDto;
public interface BookService {
    BookDto getByNameV1 (String name);

    BookDto getByNameV2 (String name);

    BookDto getByNameV3 (String name);
}
