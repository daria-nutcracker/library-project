package ru.itgirl.libraryproject.Service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.itgirl.libraryproject.dto.BookDto;
import ru.itgirl.libraryproject.dto.GenreDto;
import ru.itgirl.libraryproject.models.Book;
import ru.itgirl.libraryproject.repositories.BookRepository;

@Service
@RequiredArgsConstructor
public class BookServiceImpl implements BookService {
    private final BookRepository bookRepository;

    @Override
    public BookDto getByNameV1 (String name) {
        Book book = bookRepository.findBookByName(name).orElseThrow();
        return convertEntityToDto (book);
    }

    private BookDto convertEntityToDto (Book book) {
        return BookDto.builder()
                .id(book.getId())
                .genre(GenreDto.builder()
                        .id(book.getGenre().getId())
                        .name(book.getGenre().getName())
                        .build())
                .name(book.getName())
                .build();
    }
}
